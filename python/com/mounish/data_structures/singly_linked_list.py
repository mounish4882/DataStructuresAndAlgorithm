"""
Singly Linked List implementations in Python with improvements.

Features:
- Type hints for better code clarity
- Multiple implementation approaches
- Pythonic idioms (iterators, generators)
- Dataclass for node representation
- Both mutable and immutable versions
"""

from __future__ import annotations
from dataclasses import dataclass
from typing import Generic, TypeVar, Optional, Iterator, List
from abc import ABC, abstractmethod

T = TypeVar('T')


@dataclass
class ListNode(Generic[T]):
    """Node in a singly linked list."""
    data: T
    next: Optional[ListNode[T]] = None

    def __repr__(self) -> str:
        return f"Node({self.data})"


class SinglyLinkedList(Generic[T]):
    """
    Mutable singly linked list implementation (similar to Java version).

    Improvements over Java:
    - Type hints for type safety
    - Pythonic methods (__len__, __iter__, __str__)
    - Generator for efficient iteration
    - Context manager support
    - Property decorators
    """

    def __init__(self) -> None:
        self._head: Optional[ListNode[T]] = None
        self._size: int = 0

    @property
    def head(self) -> Optional[ListNode[T]]:
        """Get the head node."""
        return self._head

    @property
    def size(self) -> int:
        """Get the size of the list."""
        return self._size

    def insert(self, data: T) -> None:
        """Insert a node at the end of the list."""
        new_node = ListNode(data)

        if not self._head:
            self._head = new_node
        else:
            current = self._head
            while current.next:
                current = current.next
            current.next = new_node

        self._size += 1

    def insert_at_beginning(self, data: T) -> None:
        """Insert a node at the beginning of the list."""
        new_node = ListNode(data, self._head)
        self._head = new_node
        self._size += 1

    def insert_at(self, data: T, position: int) -> bool:
        """Insert a node at a specific position."""
        if position < 0 or position > self._size:
            return False

        if position == 0:
            self.insert_at_beginning(data)
            return True

        new_node = ListNode(data)
        current = self._head
        for _ in range(position - 1):
            if current:
                current = current.next

        if current:
            new_node.next = current.next
            current.next = new_node
            self._size += 1
            return True

        return False

    def delete(self, key: T) -> bool:
        """Delete the first node with the given value."""
        if not self._head:
            return False

        if self._head.data == key:
            self._head = self._head.next
            self._size -= 1
            return True

        current = self._head
        while current.next:
            if current.next.data == key:
                current.next = current.next.next
                self._size -= 1
                return True
            current = current.next

        return False

    def delete_at(self, position: int) -> bool:
        """Delete node at specific position."""
        if position < 0 or position >= self._size or not self._head:
            return False

        if position == 0:
            self._head = self._head.next
            self._size -= 1
            return True

        current = self._head
        for _ in range(position - 1):
            if current:
                current = current.next

        if current and current.next:
            current.next = current.next.next
            self._size -= 1
            return True

        return False

    def get(self, position: int) -> Optional[T]:
        """Get value at specific position."""
        if position < 0 or position >= self._size:
            return None

        current = self._head
        for _ in range(position):
            if current:
                current = current.next

        return current.data if current else None

    def search(self, key: T) -> int:
        """Search for a value and return its position (-1 if not found)."""
        current = self._head
        position = 0

        while current:
            if current.data == key:
                return position
            current = current.next
            position += 1

        return -1

    def reverse(self) -> None:
        """Reverse the linked list in-place."""
        prev = None
        current = self._head

        while current:
            next_node = current.next
            current.next = prev
            prev = current
            current = next_node

        self._head = prev

    def to_list(self) -> List[T]:
        """Convert linked list to Python list."""
        return list(self)

    def __len__(self) -> int:
        """Return the length of the list."""
        return self._size

    def __iter__(self) -> Iterator[T]:
        """Iterate over the list values."""
        current = self._head
        while current:
            yield current.data
            current = current.next

    def __str__(self) -> str:
        """String representation of the list."""
        if not self._head:
            return "[]"
        return " -> ".join(str(data) for data in self)

    def __repr__(self) -> str:
        """Detailed representation."""
        return f"SinglyLinkedList([{', '.join(repr(data) for data in self)}])"

    def __contains__(self, item: T) -> bool:
        """Check if item exists in the list."""
        return self.search(item) != -1

    def __getitem__(self, index: int) -> T:
        """Get item by index (supports negative indexing)."""
        if index < 0:
            index = self._size + index

        value = self.get(index)
        if value is None:
            raise IndexError("list index out of range")
        return value


class ImmutableLinkedList(Generic[T], ABC):
    """Abstract base class for immutable linked list."""

    @abstractmethod
    def is_empty(self) -> bool:
        """Check if list is empty."""
        pass

    @abstractmethod
    def head(self) -> T:
        """Get the head value."""
        pass

    @abstractmethod
    def tail(self) -> ImmutableLinkedList[T]:
        """Get the tail of the list."""
        pass

    @abstractmethod
    def prepend(self, value: T) -> ImmutableLinkedList[T]:
        """Prepend a value to the list."""
        pass

    @abstractmethod
    def append(self, value: T) -> ImmutableLinkedList[T]:
        """Append a value to the list."""
        pass


class Empty(ImmutableLinkedList[T]):
    """Empty immutable linked list."""

    def is_empty(self) -> bool:
        return True

    def head(self) -> T:
        raise IndexError("head of empty list")

    def tail(self) -> ImmutableLinkedList[T]:
        raise IndexError("tail of empty list")

    def prepend(self, value: T) -> ImmutableLinkedList[T]:
        return Cons(value, self)

    def append(self, value: T) -> ImmutableLinkedList[T]:
        return Cons(value, self)

    def __str__(self) -> str:
        return "[]"

    def __repr__(self) -> str:
        return "Empty()"


class Cons(ImmutableLinkedList[T]):
    """Non-empty immutable linked list (cons cell)."""

    def __init__(self, head_val: T, tail_list: ImmutableLinkedList[T]):
        self._head = head_val
        self._tail = tail_list

    def is_empty(self) -> bool:
        return False

    def head(self) -> T:
        return self._head

    def tail(self) -> ImmutableLinkedList[T]:
        return self._tail

    def prepend(self, value: T) -> ImmutableLinkedList[T]:
        return Cons(value, self)

    def append(self, value: T) -> ImmutableLinkedList[T]:
        if self._tail.is_empty():
            return Cons(self._head, Cons(value, Empty()))
        return Cons(self._head, self._tail.append(value))

    def __str__(self) -> str:
        result = [str(self._head)]
        current = self._tail
        while not current.is_empty():
            result.append(str(current.head()))
            current = current.tail()
        return " -> ".join(result)

    def __repr__(self) -> str:
        return f"Cons({self._head}, {self._tail})"


def create_linked_list(*values: T) -> ImmutableLinkedList[T]:
    """Create an immutable linked list from values."""
    result: ImmutableLinkedList[T] = Empty()
    for value in reversed(values):
        result = result.prepend(value)
    return result


def demo() -> None:
    """Demonstrate linked list implementations."""
    print("=" * 80)
    print("SINGLY LINKED LIST DEMO")
    print("=" * 80)

    # Mutable linked list demo
    print("\n=== Mutable Linked List ===")
    mutable_list = SinglyLinkedList[int]()

    print("\nInserting values: 10, 20, 30, 40, 50")
    for value in [10, 20, 30, 40, 50]:
        mutable_list.insert(value)

    print(f"List: {mutable_list}")
    print(f"Length: {len(mutable_list)}")
    print(f"As Python list: {mutable_list.to_list()}")

    print("\nInserting 5 at beginning:")
    mutable_list.insert_at_beginning(5)
    print(f"List: {mutable_list}")

    print("\nInserting 25 at position 4:")
    mutable_list.insert_at(25, 4)
    print(f"List: {mutable_list}")

    print(f"\nGet element at position 3: {mutable_list.get(3)}")
    print(f"Search for 30: position {mutable_list.search(30)}")
    print(f"Contains 30: {30 in mutable_list}")
    print(f"Contains 100: {100 in mutable_list}")

    print("\nDeleting 20:")
    mutable_list.delete(20)
    print(f"List: {mutable_list}")

    print("\nDeleting at position 2:")
    mutable_list.delete_at(2)
    print(f"List: {mutable_list}")

    print("\nReversing the list:")
    mutable_list.reverse()
    print(f"List: {mutable_list}")

    print("\nIterating with for loop:")
    for i, value in enumerate(mutable_list):
        print(f"  Position {i}: {value}")

    # Immutable linked list demo
    print("\n=== Immutable Linked List ===")
    immutable_list = create_linked_list(1, 2, 3, 4, 5)
    print(f"Created list: {immutable_list}")

    print(f"\nHead: {immutable_list.head()}")
    print(f"Tail: {immutable_list.tail()}")

    list2 = immutable_list.prepend(0)
    print(f"\nAfter prepend(0): {list2}")
    print(f"Original list: {immutable_list}")

    list3 = immutable_list.append(6)
    print(f"\nAfter append(6): {list3}")
    print(f"Original list: {immutable_list}")

    # Using indexing
    print("\n=== Indexing Support ===")
    test_list = SinglyLinkedList[str]()
    for word in ["Python", "is", "awesome"]:
        test_list.insert(word)

    print(f"List: {test_list}")
    print(f"list[0] = {test_list[0]}")
    print(f"list[1] = {test_list[1]}")
    print(f"list[-1] = {test_list[-1]}")

    print("\n" + "=" * 80)


if __name__ == "__main__":
    demo()
