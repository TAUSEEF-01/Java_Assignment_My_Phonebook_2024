import java.util.Scanner;

class Information {
    String name;
    String phone_number;

    public Information(String name, String phone_number) {
        this.name = name;
        this.phone_number = phone_number;
        process_name();
        process_phone_number();
    }

    private void process_name() {

        int n = this.name.length();

        int pos1 = 0, pos2 = n - 1;
        for (int i = 0; i < n; i++) {
            if (name.charAt(i) != ' ') {
                break;
            }
            pos1 = i;
        }
        for (int i = pos2; i > pos1; i--) {
            if (name.charAt(i) != ' ') {
                break;
            }
            pos2 = i;
        }

        String temp = "";
        for (int i = pos1; i <= pos2; i++) {
            if (name.charAt(i) == ' ') {
                temp += name.charAt(i);
                int pos = i + 1;
                while (name.charAt(pos) == ' ') {
                    pos++;
                }
                i = pos - 1;
            } else {
                temp += name.charAt(i);
            }
        }

        int sz = temp.length();
        char arr[] = new char[sz];
        for (int i = 0; i < sz; i++) {
            if (i == 0) {
                if (temp.charAt(i) >= 'a' && temp.charAt(i) <= 'z') {
                    char ch = temp.charAt(i);
                    arr[i] = (char) (ch - 'a' + 'A');
                } else {
                    char ch = temp.charAt(i);
                    arr[i] = ch;
                }
            } else if (temp.charAt(i - 1) == ' ') {
                if (temp.charAt(i) >= 'a' && temp.charAt(i) <= 'z') {
                    char ch = temp.charAt(i);
                    arr[i] = (char) (ch - 'a' + 'A');
                } else {
                    char ch = temp.charAt(i);
                    arr[i] = ch;
                }
            } else {
                if (temp.charAt(i) == ' ') {
                    char ch = temp.charAt(i);
                    arr[i] = ch;
                } else if (temp.charAt(i) >= 'A' && temp.charAt(i) <= 'Z') {
                    char ch = temp.charAt(i);
                    arr[i] = (char) (ch - 'A' + 'a');
                } else {
                    char ch = temp.charAt(i);
                    arr[i] = ch;
                }
            }
        }

        this.name = "";
        for (int i = 0; i < sz; i++) {
            name += arr[i];
        }
    }

    private void process_phone_number() {

        int n = this.phone_number.length();
        if (n == 11) {
            this.phone_number = "+880" + this.phone_number;
        }

        n = this.phone_number.length();
        char arr[] = new char[100];
        int pos = 0;
        for (int i = 0; i < n; i++) {
            if (i == 3) {
                arr[pos++] = this.phone_number.charAt(i);
                arr[pos++] = ' ';
            } else if (i == 8) {
                arr[pos++] = this.phone_number.charAt(i);
                arr[pos++] = '-';
            } else if (i == 11) {
                arr[pos++] = this.phone_number.charAt(i);
                arr[pos++] = ' ';
            } else {
                arr[pos++] = this.phone_number.charAt(i);
            }
        }

        this.phone_number = "";
        for (int i = 0; i < pos; i++) {
            this.phone_number += arr[i];
        }
    }

    String getName() {
        return this.name;
    }

    String getPhoneNumber() {
        return this.phone_number;
    }
}

public class Problem_24 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int k = sc.nextInt();
        Information info[] = new Information[k];

        String ch = sc.nextLine();
        for (int i = 0; i < k; i++) {
            String name = sc.nextLine();
            String phone_number = sc.nextLine();

            info[i] = new Information(name, phone_number);

        }

        String str = sc.nextLine();
        str = make_it_lower(str);

        String str_arr[] = new String[k];
        int id[] = new int[k];
        int cnt = 0;
        for (int i = 0; i < k; i++) {
            String si = info[i].getName();
            String temp = make_it_lower(si);

            if (is_a_substring(temp, str) == true) {
                str_arr[cnt] = si;
                id[cnt] = i;
                cnt++;
            }
        }

        sort_arr(str_arr, id, cnt);

        for (int i = 0; i < cnt; i++) {
            System.out.println(info[id[i]].getName() + " " + info[id[i]].getPhoneNumber());
        }
    }

    public static String make_it_lower(String s) {

        int sz = s.length();
        char arr[] = new char[sz];
        for (int i = 0; i < sz; i++) {
            if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                char ch = s.charAt(i);
                arr[i] = (char) (ch - 'A' + 'a');
            } else {
                arr[i] = s.charAt(i);
            }
        }

        String temp = "";
        for (int i = 0; i < sz; i++) {
            if (arr[i] == ' ')
                continue;
            temp += arr[i];
        }

        return temp;
    }

    public static boolean is_a_substring(String a, String b) {

        boolean flag = false;
        int sz_a = a.length();
        int sz_b = b.length();

        for (int i = 0; i < sz_a; i++) {
            int pos1 = i, pos2 = 0;
            while (pos1 < sz_a && pos2 < sz_b && (a.charAt(pos1) == b.charAt(pos2))) {
                pos1++;
                pos2++;
            }

            if (pos2 == sz_b) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    public static void sort_arr(String s[], int id[], int n) {

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int s1 = s[i].length();
                int s2 = s[j].length();
                int sz = min(s1, s2);

                int flag = 0;
                for (int k = 0; k < sz; k++) {
                    if (s[i].charAt(k) > s[j].charAt(k)) {
                        flag = 1;
                        int ti = id[i];
                        id[i] = id[j];
                        id[j] = ti;

                        String ts = s[i];
                        s[i] = s[j];
                        s[j] = ts;
                        break;
                    } else if (s[i].charAt(k) < s[j].charAt(k)) {
                        flag = 1;
                        break;
                    }
                }

                if (flag != 1) {
                    if (s1 > s2) {
                        int ti = id[i];
                        id[i] = id[j];
                        id[j] = ti;

                        String ts = s[i];
                        s[i] = s[j];
                        s[j] = ts;
                    }
                }
            }
        }
    }

    public static int min(int a, int b) {

        if (a < b)
            return a;
        else
            return b;
    }
}