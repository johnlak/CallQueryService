package gr.wind.smartcare.queryFromRemedy;

import java.util.HashMap;
import java.util.Map;

public class VerifyUserAuthentication
{
    private Map<String, String> userNameVsHash;

    public VerifyUserAuthentication(String userList)
    {
        String[] usersWithHashedPass = userList.split(",");
        Map<String, String> localUserNameVsHash = new HashMap<>();

        if (usersWithHashedPass.length > 0)
        {
            for (int i_user = 0; i_user < usersWithHashedPass.length; i_user++)
            {
                String[] userAndHash = usersWithHashedPass[i_user].split(":");
                if (userAndHash.length == 2)
                {
                    String user = userAndHash[0];
                    String hash = userAndHash[1];
                    //System.out.println(String.format("%s %s", user, hash));
                    localUserNameVsHash.put(user, hash);
                }
            }
        }

        this.userNameVsHash = localUserNameVsHash;
    }

    public boolean authenticates(String user, String passwd) throws Exception
    {
        try
        {
            Password pwdTool = new Password();
            if (this.userNameVsHash.containsKey(user))
            {
                String userHash = this.userNameVsHash.get(user);
                //System.out.println(String.format("%s %s %s", user, passwd, userHash));
                return pwdTool.check(passwd, userHash);
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
