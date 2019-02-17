package test.testcase;

/*
    data already in table
    test search
 */
public class TestCase04 extends TestCase03 {

    private int[] insertions = {
            1000,
            10000,
            100000
    };

    public TestCase04() {
        super("TestCase04.csv", false);
    }
}
