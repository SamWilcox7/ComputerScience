public class Driver {
    
    public static void main(String[] args) {
        IndexedUnsortedList<Integer> list = new WrappedDLL<Integer>();

        list.add(new Integer(1));
        list.add(new Integer(8));
        list.add(new Integer(3));
        list.add(new Integer(2));
        list.add(new Integer(19));
        list.add(new Integer(4));
        list.add(new Integer(11));
        list.add(new Integer(10));
        list.add(new Integer(9));

        System.out.println(list);
        System.out.println();

        Sort.sort(list);

        System.out.println(list);
    }
}
