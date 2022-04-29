package General;

public class FindMinimumWindowOfWord {
    public static void main(String[] args) {
        FindMinimumWindowOfWord s = new FindMinimumWindowOfWord();
        System.out.println(s.find("and their vision and message are stellar examples for .", new String[]{"and", "are", "for"}));
    }

    // and career potential to their employees, and their vision and message are stellar examples for
    public String find(String doc, String[] target) {
        String[] arr = doc.split(" ");
        int minLen = arr.length + 1;
        int start = -1;
        int idx = 0;
        for (int right = 0; right < arr.length; right++) {
            if (arr[right].equals(target[idx])) {
                idx++;
                if (idx == target.length) { // all words find
                    int end = right; //end idx of one possible solution
                    idx--;
                    while(idx >= 0) {
                        if (target[idx].equals(arr[right])) {
                            idx--;
                        }
                        right--;
                    }
                    if (minLen > end - right) {
                        minLen = end - right;
                        start = right + 1;
                    }

                    right = start;
                    System.out.println(right);
                    idx = 0; //reset to 0
                }
            }
        }

        return (start != -1) ? buildString(start, minLen, arr) : "";
    }

    private String buildString(int start, int minLen, String[] arr) {
        StringBuilder sb = new StringBuilder();
        int end = start;
        while(end < start + minLen)
            sb.append(arr[end++] + " ");
        System.out.println(sb.toString());
        return sb.toString().trim();
    }
}


