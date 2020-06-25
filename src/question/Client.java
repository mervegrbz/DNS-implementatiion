
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package question;
/**
 * this class creates a client with its ip address .Also each client has its own cachelist 
 * which store the ip addresses that it uses before.
 * @author merve gurbuz
 *
 */
public class Client{
	/*
	 * the Dns root that helps clients to reach other ips.
	 */
	private DnsTree root;
	/*
	 * client own ip address
	 */
	private String ipAddress;
	/*
	 * the cache of a client with finite size.
	 */
	private CachedContent[]cacheList;
	
	/**
	 * it stores the visited ip s with their domain names ip addresses and also number if visits. 
	 * This object is used in cache mechanism
	 */
	private class CachedContent
	{
		/**
		 * the domain name of address
		 */
		String domainName;
		/**
		 * the ip address of content
		 */
		String ipAddress;
		/**
		 * hit no of content
		 */
		int hitNo;
		/**
		 * constructor of cached content
		 * @param domainName domain name of content
		 * @param ipAddress ip address of content
		 */
	private CachedContent(String domainName,String ipAddress) {
		this.domainName=domainName;
		this.ipAddress=ipAddress;
		this.hitNo=1;
			}
	}
	/**
	 * constructor of client
	 * @param ipAddress ip of cllient
	 * @param root root of the dns tree that enables client to reach other ip addresses.
	 */
	public Client(String ipAddress,DnsTree root) {
		this.ipAddress=ipAddress;
		this.root=root;
		this.cacheList=new CachedContent[10];
		
	}
	/**
	 * if the given domain exist in cachelist array, method returns its ip  address else it request it from root by querydomain method of tree.
	 * after that if query domain returns a valid address addToCache method adds it to the cache. if it is not a valid domain name it return null.
	 * @param domainName the given domain name that its ip address will be returned
	 * @return address of domain name. 
	 */
	String sendRequest(String domainName) {
		for (int i=0;i<cacheList.length;i++) {
			if(cacheList[i]!=null)
			if(cacheList[i].domainName.equals(domainName)) {
				cacheList[i].hitNo++;
				
				return cacheList[i].ipAddress;
			}
			
		}
		String address=root.queryDomain(domainName);
		if(address!=null) {
			this.addToCache(domainName, address);
		}
		
		return address;		
	}
	/**
	 * this method adds new ip address with its domain name and
	 *  ip address. if domain name and ip addresses is on cache 
	 *  list it will not add it again.
	 *  while adding if the cachelist is full it search for the minimum hit content and delete it and store the new one to the end.
	 * @param domainName  domain name of new content
	 * @param ipAddress ipaddress of new content.
	 */
	public void addToCache(String domainName,String ipAddress) {
		for (CachedContent i:cacheList) 
			if(i!=null)	
			if(i.domainName.equals(domainName)&&i.ipAddress.equals(ipAddress)) 
				return;
		
		for (int i=0;i<10;i++) 
			if(cacheList[i]==null) {
				cacheList[i]=new CachedContent(domainName,ipAddress);
				return;
			}
		
		int min=0;
		int minhit=Integer.MAX_VALUE;
		for (int i=0;i<10;i++) {
			if(cacheList[i].hitNo<minhit) {
				minhit=cacheList[i].hitNo;
				min=i;
			}
		}
		for(int i=min;i<cacheList.length-1;i++) 
			cacheList[i]=cacheList[i+1];
		cacheList[9]=new CachedContent(domainName,ipAddress);
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

