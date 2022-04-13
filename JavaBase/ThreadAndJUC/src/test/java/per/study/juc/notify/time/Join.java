package per.study.juc.notify.time;

import java.util.Arrays;
import java.util.List;

public class Join
{

    public static void main(String[] args) {
        List<String> uuids = Arrays.asList("1", "2", "3");
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < uuids.size(); i++) {
            if (i + 1 != uuids.size()) {
                sb.append("'").append(uuids.get(i)).append("', ");
            }
            else {
                sb.append("'").append(uuids.get(i)).append("'");
            }
        }
        sb.append(")");
        String sql = "in " + sb;
        System.out.println(sql);
    }

}
