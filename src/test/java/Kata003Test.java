import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class Kata003Test {

    //Helpers.testMap(input_list_head, mapping_function, expected_list_head)

    @Test
    public void basicTests() {
        testMap(null, __ -> false, null);

        Function<Integer, Integer> f = x -> x * 2;
        testMap(new Kata003.Node(1, new Kata003.Node(2, new Kata003.Node(3))),
                f,
                new Kata003.Node(2, new Kata003.Node(4, new Kata003.Node(6))));
    }

    private <T, R> void testMap(Kata003.Node<T> input, Function<T, R> f, Kata003.Node<R> expected) {
        Kata003 kata003 = new Kata003();
        assertEquals(kata003.map(input, f), expected);
    }

}

