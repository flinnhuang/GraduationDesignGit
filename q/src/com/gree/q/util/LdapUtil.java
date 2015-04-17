package com.gree.q.util;

import java.util.HashMap;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.apache.log4j.Logger;

public class LdapUtil {
	
	private final static Logger log = Logger.getLogger(LdapUtil.class);
	
	public static void main(String[] uid){
		HashMap map = LdapUtil.authenticate("180023", "system32");
		log.info(map);
	}
	
	
	public final static String[] SERVERS = new String[] { "LDAP://10.1.1.2" ,"LDAP://10.1.1.2", "LDAP://10.1.1.3", "LDAP://10.1.1.1", "LDAP://10.1.1.4" };
	//public final static String[] SERVERS = new String[] { "LDAP://10.2.4.1" ,"LDAP://10.2.4.2", "LDAP://10.2.4.3" };
	
	//OU=格力电器,
	//private final static String LDAP_PATH = "DC=gree,DC=com";
	//private final static String UID_PREFIX = "gree\\";
	
	private final static String LDAP_PATH = "OU=格力电器,DC=it2004,DC=gree,DC=com,DC=cn";
	private final static String UID_PREFIX = "it2004\\";
	
	//it2004\180025
	//180025@gree.com.cn

	private static HashMap<String, String> doValidateByServer(String uid, String psw, String server) {

		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		// set security credentials, note using simple cleartext authentication
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, UID_PREFIX + uid);
		env.put(Context.SECURITY_CREDENTIALS, psw);
		// connect to my domain controller
		env.put(Context.PROVIDER_URL, server);

		HashMap<String, String> ret = null;
		
		LdapContext ctx = null;
		try {
			ctx = new InitialLdapContext(env, null);
			SearchControls searchCtls = new SearchControls();
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			// specify the LDAP search filter
			// String searchFilter =
			// "(&(objectCategory=person)(objectClass=user)(name=180025))";
			String searchFilter = "(&(objectCategory=person)(objectClass=user)(name="
					+ uid + "))";

			 String returnedAtts[] = { "displayname", "department", "company", "mail" };
//			String returnedAtts[] = { "displayname" };
			searchCtls.setReturningAttributes(returnedAtts);

			// Search for objects using the filter
			NamingEnumeration<SearchResult> answer = ctx.search(LDAP_PATH, searchFilter, searchCtls);

			// Loop through the search results
			if (answer.hasMoreElements()) {
				
				ret = new HashMap<String, String>();
				ret.put("server", server);
				
				SearchResult sr = answer.next();

				Attributes attrs = sr.getAttributes();
				
				System.out.println(attrs);
				if (attrs != null) {
					try {
						for (int i = 0; i < returnedAtts.length; i++) {
							ret.put(attrs.get(returnedAtts[i]).getID(), String
									.valueOf(attrs.get(returnedAtts[i]).get()));
						}

						return ret;
					} catch (Exception e) {
						log.error(e, e);
					}
				}
			}
			
		} catch (NamingException e) {
			log.error(server+":"+e.getMessage());
			//log.error(e);
			
		} finally {
			if (ctx != null) {
				try {
					ctx.close();
				} catch (NamingException e) {
					log.error(e, e);
				}
			}
		}

		return null;
	}
	
	public static HashMap<String, String> authenticate(String uid, String psw) {
		HashMap<String, String> resultMap = null;
		for(String server : SERVERS){
			if ((resultMap = doValidateByServer(uid, psw, server)) != null) {
				log.info(uid +"@" + server + "登录成功");
				break;
			}
		}
		return resultMap;
	}

}
