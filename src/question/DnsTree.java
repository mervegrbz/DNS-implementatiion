
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package question;

import java.util.*;
/**
 * it is the structure of DNS system.
 * @author merve
 *
 */
public class DnsTree {
	/**
	 * root of the DNS
	 */
	DnsNode root;
	/**
	 * Map of the valid domains
	 */
	Map<String, Set<String>> validdomains;
	/**
	 * constructor of Dns tree that initialize root.
	 */
	public DnsTree() {
		this.root = new DnsNode();
		validdomains = new HashMap<String, Set<String>>();
	}
	/**
	 * it inserts the new record to  given domain name. if tree does not have that domain name 
	 * it creates new node and makes its validity true. while doing that it iterate in childnode list if it finds the it enters it and that search again until the full name is found
	 * @param domainName the domain name that new record will be inserted.
	 * @param ipAddress new record of doamin name
	 */
	public void insertRecord(String domainName, String ipAddress) {
		DnsNode current = root;
		DnsNode childNode = null;

		ArrayList<String> arr = name(domainName);
		for (int i = arr.size() - 1; i >= 0; i--) {

			String name = arr.get(i);
			childNode = current.getChildNodeList().get(name);
			if (childNode == null) {
				childNode = new DnsNode();
				current.getChildNodeList().put(name, childNode);
			}

			current = childNode;
		}
		current.getIpAddresses().add(ipAddress);
		current.setValidDomain(true);
		current.ipaddress.add(ipAddress);
		validdomains.put(domainName, current.getIpAddresses());
	}
	/**
	 * it remove all the record of given domain name and makes its valid domain to false. if it does not and child node ,
	 *  child node will be deleted from the tree.
	 * @param domainName the domain name that will be deleted
	 * @return return whether the removal is done successfully or not
	 */
	public boolean removeRecord(String domainName) {
		DnsNode current = root;
		DnsNode parent = null;
		ArrayList<String> arr = name(domainName);
		for (int i = arr.size() - 1; i >= 0; i--) {

			String name = arr.get(i);
			if (i == 0) {
				parent = current;
			}
			current = current.getChildNodeList().get(name);
			if (current == null)
				return false;

		}
			current.getIpAddresses().clear();
			current.ipaddress.clear();
			current.setValidDomain(false);
			validdomains.remove(domainName);
			if (current.getChildNodeList().isEmpty()) {
				parent.getChildNodeList().remove(domainName);
				current = null;
			} 
		return true;
	}
/**
 * it remove the given id from from domain name, after that if ip addresses list is empty validt will be false.
 * 
 * @param domainName the domain name that the ip address will be deleted
 * @param ipAddress deleted ip address
 * @return whether the process is done successfully or not.
 */
	
	public boolean removeRecord(String domainName, String ipAddress) {
		DnsNode current = root;
		DnsNode parent = null;
		ArrayList<String> arr = name(domainName);
		for (int i = arr.size() - 1; i >= 0; i--) {

			String name = arr.get(i);
			if (i == 0) {
				parent = current;
			}
			current = current.getChildNodeList().get(name);
			if (current == null)
				return false;
		}

		if (current.getIpAddresses().contains(ipAddress)) {
			current.getIpAddresses().remove(ipAddress);
			current.ipaddress.remove(ipAddress);
			if (current.getIpAddresses().isEmpty()) {
				validdomains.remove(domainName);
				if (current.getChildNodeList().isEmpty()) 
					parent.getChildNodeList().remove(domainName);
				current.setValidDomain(false);
			}
			return true;
		}
		return false;
	}
	/**
	 * it works like round robin algorithm  which means all ip addresses of  given domain name will be returned in an order.
	 * if there is no such available ip it returns null
	 * @param domainName the requested domain name 
	 * @return the ip address of domain name
	 */
	public String queryDomain(String domainName) {
		DnsNode current = root;
		ArrayList<String> arr = name(domainName);
		for (int i = arr.size() - 1; i >= 0; i--) {

			String name = arr.get(i);
			current = current.getChildNodeList().get(name);
			if (current == null)
				return null;
		}
		if(current.isValidDomain()) {
		if (current.getIndex() == current.getIpAddresses().size())
			current.setIndex(0);
		String ip = current.ipaddress.get(current.getIndex());
		current.setIndex(current.getIndex() + 1);	
		return ip;
	}
		return null;
		}

	public Map<String, Set<String>> getAllRecords() {

		return validdomains;

	}
	/**
	 * it gives the nodes name 
	 * @param domainName domain name
	 * @return the nodes name in array
	 */
	public static ArrayList<String> name(String domainName) {
		ArrayList<String> arr = new ArrayList<String>();
		while (domainName.contains(".")) {
			arr.add(domainName);
			domainName = domainName.substring(domainName.indexOf(".") + 1);
		}
		arr.add(domainName);
		return arr;
	}

}
//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
