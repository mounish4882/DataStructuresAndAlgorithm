"""
Threading and Concurrency examples in Python.

Demonstrates:
- Traditional threading
- Thread pools
- Locks and synchronization
- Async/await with asyncio
- Multiprocessing
- Producer-Consumer pattern
- Context managers for threads
"""

import threading
import time
import queue
from concurrent.futures import ThreadPoolExecutor, ProcessPoolExecutor, as_completed
from threading import Thread, Lock, Semaphore, Event
from typing import List
import asyncio
from dataclasses import dataclass


# ============================================================================
# 1. Basic Threading Examples
# ============================================================================

class ThreadExtendingThread(Thread):
    """Thread created by extending Thread class (similar to Java)."""

    def __init__(self, name: str, count: int = 5):
        super().__init__(name=name)
        self.count = count

    def run(self):
        """Override run method."""
        for i in range(1, self.count + 1):
            print(f"{self.name} - Count: {i}")
            time.sleep(0.5)
        print(f"{self.name} completed")


def thread_function(name: str, count: int = 5):
    """Function to be run in a thread."""
    for i in range(1, count + 1):
        print(f"{name} - Count: {i}")
        time.sleep(0.5)
    print(f"{name} completed")


def demo_basic_threading():
    """Demonstrate basic threading approaches."""
    print("\n" + "=" * 80)
    print("BASIC THREADING")
    print("=" * 80)

    # Method 1: Extending Thread class
    print("\n=== Method 1: Extending Thread Class ===")
    thread1 = ThreadExtendingThread("Thread-1", 3)
    thread2 = ThreadExtendingThread("Thread-2", 3)

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()
    print("Both threads completed\n")

    # Method 2: Using Thread with target function
    print("=== Method 2: Thread with Target Function ===")
    thread1 = Thread(target=thread_function, args=("Worker-1", 3))
    thread2 = Thread(target=thread_function, args=("Worker-2", 3))

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()
    print("Both threads completed\n")

    # Method 3: Using lambda
    print("=== Method 3: Thread with Lambda ===")
    thread = Thread(target=lambda: print("Lambda thread executed"))
    thread.start()
    thread.join()
    print()


# ============================================================================
# 2. Stoppable Thread
# ============================================================================

class StoppableThread(Thread):
    """Thread that can be gracefully stopped."""

    def __init__(self, name: str):
        super().__init__(name=name)
        self._stop_event = Event()
        self.count = 0

    def stop(self):
        """Signal the thread to stop."""
        self._stop_event.set()

    def stopped(self) -> bool:
        """Check if stop was requested."""
        return self._stop_event.is_set()

    def run(self):
        """Run until stop is requested."""
        while not self.stopped():
            self.count += 1
            print(f"{self.name} - Count: {self.count}")
            time.sleep(0.3)
        print(f"{self.name} stopped gracefully after {self.count} iterations")


def demo_stoppable_thread():
    """Demonstrate stoppable thread."""
    print("=" * 80)
    print("STOPPABLE THREAD")
    print("=" * 80 + "\n")

    thread = StoppableThread("Stoppable-Thread")
    thread.start()

    time.sleep(2)  # Let it run for 2 seconds
    print("Requesting thread to stop...")
    thread.stop()
    thread.join()
    print()


# ============================================================================
# 3. Thread Synchronization
# ============================================================================

class Counter:
    """Thread-safe counter using Lock."""

    def __init__(self):
        self._value = 0
        self._lock = Lock()

    def increment(self):
        """Increment counter (thread-safe)."""
        with self._lock:
            self._value += 1

    @property
    def value(self) -> int:
        """Get current value (thread-safe)."""
        with self._lock:
            return self._value


class UnsafeCounter:
    """Counter without synchronization (for comparison)."""

    def __init__(self):
        self._value = 0

    def increment(self):
        """Increment counter (not thread-safe)."""
        self._value += 1

    @property
    def value(self) -> int:
        """Get current value."""
        return self._value


def increment_counter(counter, iterations: int):
    """Increment counter multiple times."""
    for _ in range(iterations):
        counter.increment()


def demo_synchronization():
    """Demonstrate thread synchronization."""
    print("=" * 80)
    print("THREAD SYNCHRONIZATION")
    print("=" * 80)

    iterations_per_thread = 10000
    num_threads = 10

    # Safe counter with lock
    print("\n=== Thread-Safe Counter (with Lock) ===")
    safe_counter = Counter()
    threads = []

    for i in range(num_threads):
        thread = Thread(target=increment_counter, args=(safe_counter, iterations_per_thread))
        threads.append(thread)
        thread.start()

    for thread in threads:
        thread.join()

    expected = num_threads * iterations_per_thread
    print(f"Expected: {expected:,}")
    print(f"Actual:   {safe_counter.value:,}")
    print(f"Correct:  {safe_counter.value == expected} ✓" if safe_counter.value == expected else "Incorrect ✗")

    # Unsafe counter without lock
    print("\n=== Unsafe Counter (without Lock) ===")
    unsafe_counter = UnsafeCounter()
    threads = []

    for i in range(num_threads):
        thread = Thread(target=increment_counter, args=(unsafe_counter, iterations_per_thread))
        threads.append(thread)
        thread.start()

    for thread in threads:
        thread.join()

    print(f"Expected: {expected:,}")
    print(f"Actual:   {unsafe_counter.value:,}")
    print(f"Correct:  {unsafe_counter.value == expected} ✓" if unsafe_counter.value == expected else f"Lost {expected - unsafe_counter.value:,} increments ✗")
    print()


# ============================================================================
# 4. Producer-Consumer Pattern
# ============================================================================

def producer(q: queue.Queue, producer_id: int, items: int):
    """Produce items and put them in queue."""
    for i in range(items):
        item = f"Item-{producer_id}-{i}"
        q.put(item)
        print(f"Producer {producer_id} produced: {item} (Queue size: {q.qsize()})")
        time.sleep(0.1)
    print(f"Producer {producer_id} finished")


def consumer(q: queue.Queue, consumer_id: int, sentinel):
    """Consume items from queue."""
    while True:
        item = q.get()
        if item is sentinel:
            q.task_done()
            break

        print(f"Consumer {consumer_id} consumed: {item} (Queue size: {q.qsize()})")
        time.sleep(0.15)
        q.task_done()

    print(f"Consumer {consumer_id} finished")


def demo_producer_consumer():
    """Demonstrate producer-consumer pattern."""
    print("=" * 80)
    print("PRODUCER-CONSUMER PATTERN")
    print("=" * 80 + "\n")

    q = queue.Queue(maxsize=10)
    sentinel = object()  # Unique sentinel object

    # Create producers and consumers
    producers = [
        Thread(target=producer, args=(q, i, 5))
        for i in range(2)
    ]

    consumers = [
        Thread(target=consumer, args=(q, i, sentinel))
        for i in range(3)
    ]

    # Start all threads
    for p in producers:
        p.start()
    for c in consumers:
        c.start()

    # Wait for producers to finish
    for p in producers:
        p.join()

    # Signal consumers to stop
    for _ in consumers:
        q.put(sentinel)

    # Wait for consumers to finish
    for c in consumers:
        c.join()

    print("Producer-Consumer demo completed\n")


# ============================================================================
# 5. Thread Pool
# ============================================================================

def task_function(task_id: int) -> int:
    """A task to be executed in thread pool."""
    thread_name = threading.current_thread().name
    print(f"Task {task_id} running on {thread_name}")
    time.sleep(0.5)
    return task_id ** 2


def demo_thread_pool():
    """Demonstrate thread pool executor."""
    print("=" * 80)
    print("THREAD POOL EXECUTOR")
    print("=" * 80 + "\n")

    with ThreadPoolExecutor(max_workers=4) as executor:
        # Submit tasks
        futures = [executor.submit(task_function, i) for i in range(10)]

        # Collect results
        print("\nResults:")
        for future in as_completed(futures):
            result = future.result()
            print(f"  Task result: {result}")

    print("\nAll tasks completed\n")


# ============================================================================
# 6. Async/Await with asyncio
# ============================================================================

async def async_task(task_id: int, duration: float):
    """Async task that sleeps for duration."""
    print(f"Async Task {task_id} started")
    await asyncio.sleep(duration)
    print(f"Async Task {task_id} completed after {duration}s")
    return task_id * 2


async def async_producer(queue: asyncio.Queue, producer_id: int, items: int):
    """Async producer."""
    for i in range(items):
        item = f"AsyncItem-{producer_id}-{i}"
        await queue.put(item)
        print(f"Async Producer {producer_id} produced: {item}")
        await asyncio.sleep(0.1)


async def async_consumer(queue: asyncio.Queue, consumer_id: int):
    """Async consumer."""
    while True:
        item = await queue.get()
        if item is None:  # Sentinel
            break
        print(f"Async Consumer {consumer_id} consumed: {item}")
        await asyncio.sleep(0.15)
        queue.task_done()


async def demo_asyncio_tasks():
    """Demonstrate asyncio with concurrent tasks."""
    print("=" * 80)
    print("ASYNCIO - CONCURRENT TASKS")
    print("=" * 80 + "\n")

    # Run multiple tasks concurrently
    tasks = [
        async_task(1, 1.0),
        async_task(2, 0.5),
        async_task(3, 0.8),
        async_task(4, 0.3),
    ]

    results = await asyncio.gather(*tasks)
    print(f"\nResults: {results}\n")


async def demo_asyncio_producer_consumer():
    """Demonstrate async producer-consumer."""
    print("=" * 80)
    print("ASYNCIO - PRODUCER-CONSUMER")
    print("=" * 80 + "\n")

    queue = asyncio.Queue(maxsize=10)

    # Create producers and consumers
    producers = [
        asyncio.create_task(async_producer(queue, i, 3))
        for i in range(2)
    ]

    consumers = [
        asyncio.create_task(async_consumer(queue, i))
        for i in range(2)
    ]

    # Wait for producers to complete
    await asyncio.gather(*producers)

    # Signal consumers to stop
    for _ in consumers:
        await queue.put(None)

    # Wait for consumers to complete
    await asyncio.gather(*consumers)

    print("\nAsync Producer-Consumer completed\n")


# ============================================================================
# 7. Comparison: Threading vs Asyncio
# ============================================================================

def io_bound_task_sync(task_id: int):
    """Synchronous I/O-bound task."""
    time.sleep(0.5)
    return task_id


async def io_bound_task_async(task_id: int):
    """Asynchronous I/O-bound task."""
    await asyncio.sleep(0.5)
    return task_id


def demo_threading_vs_asyncio():
    """Compare threading and asyncio performance."""
    print("=" * 80)
    print("PERFORMANCE: THREADING VS ASYNCIO")
    print("=" * 80)

    num_tasks = 20

    # Threading approach
    print(f"\n=== Threading ({num_tasks} tasks) ===")
    start = time.time()
    with ThreadPoolExecutor(max_workers=10) as executor:
        results = list(executor.map(io_bound_task_sync, range(num_tasks)))
    threading_time = time.time() - start
    print(f"Time: {threading_time:.2f}s")

    # Asyncio approach
    print(f"\n=== Asyncio ({num_tasks} tasks) ===")
    async def run_async_tasks():
        tasks = [io_bound_task_async(i) for i in range(num_tasks)]
        return await asyncio.gather(*tasks)

    start = time.time()
    results = asyncio.run(run_async_tasks())
    asyncio_time = time.time() - start
    print(f"Time: {asyncio_time:.2f}s")

    print(f"\nSpeedup: {threading_time / asyncio_time:.2f}x faster with asyncio\n")


# ============================================================================
# Main Demo
# ============================================================================

def demo():
    """Run all concurrency demonstrations."""
    print("\n" + "=" * 80)
    print("PYTHON THREADING AND CONCURRENCY EXAMPLES")
    print("=" * 80 + "\n")

    try:
        demo_basic_threading()
        time.sleep(0.5)

        demo_stoppable_thread()
        time.sleep(0.5)

        demo_synchronization()
        time.sleep(0.5)

        demo_producer_consumer()
        time.sleep(0.5)

        demo_thread_pool()
        time.sleep(0.5)

        # Asyncio examples
        asyncio.run(demo_asyncio_tasks())
        asyncio.run(demo_asyncio_producer_consumer())

        demo_threading_vs_asyncio()

    except Exception as e:
        print(f"Error in concurrency demo: {e}")
        import traceback
        traceback.print_exc()

    print("=" * 80)
    print("ALL CONCURRENCY EXAMPLES COMPLETED")
    print("=" * 80 + "\n")


if __name__ == "__main__":
    demo()
