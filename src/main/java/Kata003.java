/**
 * Implement the method map, which accepts a linked list (head) and a mapping function,
 * and returns a new linked list (head) where every element is the result of applying
 * the given mapping method to each element of the original list.
 * <p>
 * Make sure you do not modify the original list!
 * <p>
 * For example: Given the list: 1 -> 2 -> 3, and the mapping function x => x * 2, map should return 2 -> 4 -> 6
 * <p>
 * The linked list is defined as follows:
 * <p>
 * class Node<T> {
 * public T data;
 * public Node<T> next;
 * <p>
 * Node(T data, Node next) {
 * this.data = data;
 * this.next = next;
 * }
 * <p>
 * Node(T data) {
 * this(data, null);
 * }
 * }
 * Note: the list may be null and can hold any type of value.
 * <p>
 * https://www.codewars.com/kata/fun-with-lists-map/train/java
 */

import java.util.Optional;
import java.util.function.Function;

public class Kata003 {
    static <T, R> Node<R> map(Node<T> head, Function<T, R> f) {
        return Optional.ofNullable(head).map(node -> {
            return new Node<>(f.apply(node.data), map(node.next, f));
        }).orElse(null);
    }

    @lombok.EqualsAndHashCode
    public static class Node<T> {
        public T data;
        public Node<T> next;

        Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        Node(T data) {
            this(data, null);
        }
    }
}

