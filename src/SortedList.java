import java.util.ArrayList;

/**
 * Custom list implementation that maintains strings in sorted ascending order.
 * Uses binary search for efficient insertion and searching.
 */
public class SortedList {
    private ArrayList<String> list;

    public SortedList() {
        list = new ArrayList<>();
    }

    /**
     * Adds a string to the list in sorted order using binary search to find the insertion position.
     */
    public void add (String element) {
        if (list.isEmpty()) {
            list.add(element);
            return;
        }

        int position = binarySearchInsertPosition(element);
        list.add(position, element);
    }

    /**
     * Binary search to find the position where element should be inserted to maintain sorted order.
     */
    private int binarySearchInsertPosition(String element) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = element.compareTo(list.get(mid));

            if (comparison < 0) {
                right = mid - 1;
            } else if (comparison > 0) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

    /** Searches for an element using binary search.
     * Returns the index if found, or -1 if not found.
     */
    public int search(String element) {
        return binarySearch(element, 0, list.size() - 1);
    }

    /**
     * Binary search implementation to find an element.
     * Returns index if found, or -1 if not found.
     */
    private int binarySearch(String element, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;
        int comparison = element.compareTo(list.get(mid));

        if (comparison == 0) {
            return mid;
        } else if (comparison < 0) {
            return binarySearch(element, left, mid - 1);
        } else {
            return binarySearch(element, mid + 1, right);
        }
    }

    /**
     * Finds where an element would be inserted if not found.
     */
    public int findInsertPosition(String element) {
        return binarySearchInsertPosition(element);
    }

    /**
     * Returns the element at the specified index.
     */
    public String get(int index) {
        return list.get(index);
    }

    /**
     * Returns the size of the list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns all elements as a formatted string.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(i).append(": ").append(list.get(i)).append("\n");
        }
        return sb.toString();
    }
}
