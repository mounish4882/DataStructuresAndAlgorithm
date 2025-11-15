package com.mounish.DataStructures

/**
 * Singly Linked List implementation in Scala with improvements:
 * - Immutable and mutable versions
 * - Functional programming approach
 * - Pattern matching for cleaner code
 * - Tail recursion for better performance
 * - Type parameterization for generics
 */

// Immutable version (functional approach)
sealed trait LinkedList[+A] {
  def isEmpty: Boolean
  def head: A
  def tail: LinkedList[A]

  def prepend[B >: A](elem: B): LinkedList[B] = Node(elem, this)

  def append[B >: A](elem: B): LinkedList[B] = this match {
    case Empty => Node(elem, Empty)
    case Node(h, t) => Node(h, t.append(elem))
  }

  def delete[B >: A](elem: B): LinkedList[B] = this match {
    case Empty => Empty
    case Node(h, t) if h == elem => t
    case Node(h, t) => Node(h, t.delete(elem))
  }

  def count: Int = this match {
    case Empty => 0
    case Node(_, t) => 1 + t.count
  }

  def printAll(): Unit = {
    def loop(list: LinkedList[A]): Unit = list match {
      case Empty => println()
      case Node(h, Empty) => println(h)
      case Node(h, t) =>
        print(s"$h -> ")
        loop(t)
    }
    loop(this)
  }

  def get(index: Int): Option[A] = {
    @scala.annotation.tailrec
    def loop(list: LinkedList[A], i: Int): Option[A] = list match {
      case Empty => None
      case Node(h, _) if i == 0 => Some(h)
      case Node(_, t) => loop(t, i - 1)
    }
    loop(this, index)
  }

  def toList: List[A] = this match {
    case Empty => Nil
    case Node(h, t) => h :: t.toList
  }
}

case object Empty extends LinkedList[Nothing] {
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("head of empty list")
  def tail: LinkedList[Nothing] = throw new NoSuchElementException("tail of empty list")
}

case class Node[A](head: A, tail: LinkedList[A]) extends LinkedList[A] {
  def isEmpty: Boolean = false
}

// Mutable version (similar to Java implementation)
class MutableSinglyLinkedList[A] {
  private class ListNode(var data: A, var next: Option[ListNode] = None)

  private var head: Option[ListNode] = None
  private var size: Int = 0

  def insert(data: A): Unit = {
    val newNode = new ListNode(data)

    head match {
      case None =>
        head = Some(newNode)
      case Some(h) =>
        var current = h
        while (current.next.isDefined) {
          current = current.next.get
        }
        current.next = Some(newNode)
    }
    size += 1
  }

  def delete(key: A): Boolean = {
    head match {
      case None => false
      case Some(h) if h.data == key =>
        head = h.next
        size -= 1
        true
      case Some(h) =>
        var current = h
        while (current.next.isDefined && current.next.get.data != key) {
          current = current.next.get
        }
        current.next match {
          case Some(nodeToDelete) =>
            current.next = nodeToDelete.next
            size -= 1
            true
          case None => false
        }
    }
  }

  def printAll(): Unit = {
    head match {
      case None => println("List is empty")
      case Some(h) =>
        var current: Option[ListNode] = Some(h)
        while (current.isDefined) {
          print(s"${current.get.data}")
          current = current.get.next
          if (current.isDefined) print(" -> ")
        }
        println()
    }
  }

  def count: Int = size

  def printAt(pos: Int): Option[A] = {
    if (pos < 0 || pos >= size) {
      None
    } else {
      var current = head
      var index = 0
      while (index < pos && current.isDefined) {
        current = current.get.next
        index += 1
      }
      current.map(_.data)
    }
  }

  def toList: List[A] = {
    def loop(node: Option[ListNode], acc: List[A]): List[A] = node match {
      case None => acc.reverse
      case Some(n) => loop(n.next, n.data :: acc)
    }
    loop(head, Nil)
  }
}

object SinglyLinkedList {
  def apply[A](elements: A*): LinkedList[A] = {
    elements.foldRight[LinkedList[A]](Empty)((elem, list) => Node(elem, list))
  }

  def demo(): Unit = {
    println("=== Immutable LinkedList Demo ===")
    val list1 = SinglyLinkedList(1, 2, 3, 4, 5)
    print("List: ")
    list1.printAll()
    println(s"Count: ${list1.count}")
    println(s"Element at index 2: ${list1.get(2)}")

    val list2 = list1.append(6)
    print("After append(6): ")
    list2.printAll()

    val list3 = list2.delete(3)
    print("After delete(3): ")
    list3.printAll()

    println("\n=== Mutable LinkedList Demo ===")
    val mutableList = new MutableSinglyLinkedList[Long]()
    mutableList.insert(10L)
    mutableList.insert(20L)
    mutableList.insert(30L)
    mutableList.insert(40L)

    print("List: ")
    mutableList.printAll()
    println(s"Count: ${mutableList.count}")
    println(s"Element at position 2: ${mutableList.printAt(2)}")

    mutableList.delete(20L)
    print("After delete(20): ")
    mutableList.printAll()
  }
}
