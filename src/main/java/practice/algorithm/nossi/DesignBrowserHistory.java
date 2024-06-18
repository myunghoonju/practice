package practice.algorithm.nossi;

import java.util.LinkedList;

// https://leetcode.com/problems/design-browser-history/description/
public class DesignBrowserHistory {

    int idx;
    LinkedList<String> history = new LinkedList<>();

    public DesignBrowserHistory(String page) {
        idx = 0;
        history.add(page);
    }

    public void visit(String url) {
        idx++;
        if (idx == history.size()) {
            history.add(url);
        } else {
            history.set(idx, url);
        }

        while (idx < history.size() - 1) {
            history.removeLast();
        }
    }

    public String back(int steps) {
        idx = Math.max(idx - steps, 0);
        return history.get(idx);
    }

    public String forward(int steps) {
        idx = Math.min(idx + steps, history.size() - 1);
        return history.get(idx);
    }
}
