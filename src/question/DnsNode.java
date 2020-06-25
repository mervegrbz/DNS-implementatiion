
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package question;

import java.util.*;
/**
 * it is the node of dns tree which has map of childnodes, validity of itself
 *  and set of the ipaddresses  of that node. 
 *
 */
public class DnsNode{
	/**
	 * the map of the child nodes with their sub nodes and keys.
	 */
	private Map<String,DnsNode>childNodeList;
	/**
	 * whether the node is valid or not
	 */
	private boolean validDomain;
	/**
	 * the list of the ip addresses of node
	 */
	private Set<String>ipAddresses;
	/**
	 * index of which ip addresses has wanted by querydomain
	 */
	private int index=0;
	/**
	 * the arraylist to find  next ip address 
	 */
	ArrayList<String> ipaddress;
	/**
	 * constructor of Dns node.
	 
	 */
	public DnsNode(){
		setChildNodeList(new TreeMap<String,DnsNode>());
		setIpAddresses(new LinkedHashSet<String>());
		setValidDomain(false);
		ipaddress = new ArrayList<String>();
		
		
	}
	public Map<String,DnsNode> getChildNodeList() {
		return childNodeList;
	}
	public void setChildNodeList(Map<String,DnsNode> childNodeList) {
		this.childNodeList = childNodeList;
	}
	public Set<String> getIpAddresses() {
		return ipAddresses;
	}
	public void setIpAddresses(Set<String> ipAddresses) {
		this.ipAddresses = ipAddresses;
	}
	public boolean isValidDomain() {
		return validDomain;
	}
	public void setValidDomain(boolean validDomain) {
		this.validDomain = validDomain;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	
	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

