import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main
{
    public static void main(String[] args) throws java.io.IOException
    {
        int count = 0 ;
        String line;
        String next_p;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        count = Integer.parseInt(br.readLine());
        int i = 0;
        while( i < count)
        {
            line = br.readLine();
            next_p = getNextPalindrome(line);
            System.out.println(next_p);
            ++i;
        }
    }
    static String getNextPalindrome( String line)
    {
        if( line.length() < 2 || line == "11")
        {
            return "11";
        }

        int left_len = line.length() / 2;
        int center_len = line.length() % 2;
        int right_len = line.length() - left_len - center_len;
        String left = line.substring(0, left_len);
        String center = line.substring(left_len, left_len+center_len);
        String right = line.substring(left_len+center_len, left_len+center_len+right_len);
        String rleft = new StringBuilder(left).reverse().toString();
        
        String next_right = incString( right);
        int next_right_len = next_right.length();
        int comp = next_right.compareTo(rleft);
        if ( next_right_len == left_len && (comp == 0 || comp < 1))
        {
            return left + center + rleft;
        }

        String next_left_center = incString(left+center);
        int next_left_center_len = next_left_center.length();
        int next_left_len = left_len;
        int next_center_len = center_len;
        
        if(next_left_center_len > left_len + center_len)
        {
            if(center_len == 0)
            {
                next_center_len = 1;
                next_left_len = left_len;
            }else
            {
                next_center_len = 0;
                next_left_len = left_len + 1;
            }
        }
        String next_left = next_left_center.substring(0, next_left_len);
        String next_center = next_left_center.substring(next_left_len, next_left_len + next_center_len);
        String rnext_left = new StringBuilder(next_left).reverse().toString();

        return next_left + next_center + rnext_left;
    }

    static String incString( String line)
    {
        char[] ret = new StringBuilder(line).reverse().toString().toCharArray();
        int id = 0;
        int line_len = line.length();
        boolean stillinc = true;
        while (id < line_len)
        {
            if( stillinc)
            {
                if(ret[id] == '9')
                {
                    ret[id] = '0';
                }else
                {
                    ret[id] = (char)((int)(ret[id])+1);
                    stillinc = false;
                }
            }else
            {
                break;
            }
            ++id;
        }
        if(stillinc)
        {
            return "1" + (new StringBuilder(new String(ret)).reverse().toString());
        }
        return new StringBuilder(new String(ret)).reverse().toString();
    }
}
