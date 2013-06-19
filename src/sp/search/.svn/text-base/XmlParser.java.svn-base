package sp.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XmlParser {
	public  Section[] sections = null;

	public XmlParser(String url) {
		getXMLObject(url);
	}

	public int length() {
		if(isSections())
			return sections.length;
		else
			return 0;
	}

	public int docLength(int i) {
		return sections[i].length();
	}
	
	public boolean isSections() {
		if(sections == null || sections.length==0)
			return false;
		else
			return true;
	}

	public boolean isDocs(int i) {
		return sections[i].isDocs();
	}

	public String getInfoValue(int i, String name) {
		return sections[i].getInfoValue(name);
	}
	
	public String getName(int i) {
		String value=new String();
		try{
			value = sections[i].getName();
		}catch(Exception e){
			return "";
		}
		return value;
	}

	public String getDocValue(int i, int j, String name){
		String value=new String();
		
		try {
			value = sections[i].getDocValue(j, name);
		} catch(Exception e) {
			return "";
		}
		return value;
	}

	public String[] getDocField(int i) {
		Set b = sections[i].docs[0].getDocData().keySet();

		return (String[])b.toArray();
	}

	//xml 파싱 메소드////////////////////////////////////////
	private void getXMLObject(String url)  {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(url);            
			sections = this.getData(doc);
			
		} catch(Exception e) {
			e.printStackTrace();
			sections = null;
		}
	}

	private Section[] getData(Document doc) {
		Section[] section = null;
		ArrayList sectionlist = null;
		
		try {

			/*meta_storage_list element*/
			Element root = doc.getDocumentElement();

			sectionlist = new ArrayList();
			NodeList member = root.getChildNodes();

			section=new Section[member.getLength()];
			
			for(int i=0; i<member.getLength(); i++){
				Node memberNode = member.item(i);

				if(memberNode.getNodeType() != Node.ELEMENT_NODE)   continue;

				/*section element*/
				if(memberNode.hasChildNodes()) {
					sectionlist.add(getMasterData(memberNode));
				}
			}

			//save section array
			if(sectionlist.size() != 0){
				section = new Section[sectionlist.size()];
				for(int i=0; i<sectionlist.size(); i++)   section[i] = (Section)sectionlist.get(i);
			}else{
				sectionlist = null;
			}

		}catch(Exception e){
			e.printStackTrace();
			section = null;
			return section;
		}
		return section;
	}

	private HashMap setAttributes(Node node) {
		HashMap attMap = new HashMap();
		if(node.hasAttributes()) {
			NamedNodeMap attr = node.getAttributes();
			for(int i=0; i<attr.getLength(); i++) {
				Node attNode = attr.item(i);
				attMap.put(attNode.getNodeName(), attNode.getNodeValue());
			}
		}
		return attMap;
	}

	private HashMap setAttributes(Node node, HashMap attMap) {
		NodeList nodeList = node.getChildNodes();
		
		for(int i=0; i<nodeList.getLength(); i++) {
			Node childnode = nodeList.item(i);
			
			if(childnode.getNodeType() == Node.ELEMENT_NODE) {
				if(childnode.hasAttributes()) {
					NamedNodeMap attrMap = childnode.getAttributes();
					Node nameNode = attrMap.getNamedItem("name");
					if(childnode.hasChildNodes()) {
						attMap.put(nameNode.getNodeValue(), childnode.getFirstChild().getNodeValue());
					}
				}
			}
		}
		
		return attMap;
	}

	private ArrayList setChildren(Node node, HashMap attMap) {
		NodeList child = node.getChildNodes();
		ArrayList docList = new ArrayList();

		for(int i=0; i< child.getLength(); i++) {
			Node childNode = child.item(i);

			if(childNode.getNodeType() != Node.ELEMENT_NODE)    continue;

			/*master element*/
			if(childNode.hasAttributes()) {
				if("doc".equals(childNode.getNodeName())){
					Doc doc = new Doc();
					doc = getDocData(childNode);
					docList.add(doc);
				}
			} else attMap.put(childNode.getNodeName(), childNode.getFirstChild().getNodeValue());
		}
		
		return docList;
	}

	private Section getMasterData(Node memberNode) {
		Section section = new Section();
		Doc[] docs = null;
		ArrayList docArr = null;
		HashMap arrMaster = new HashMap();
		
		try{
			arrMaster=setAttributes(memberNode);
			docArr = setChildren(memberNode, arrMaster);
			section.setInfo(arrMaster);


			if(docArr.size() != 0){
				docs = new Doc[docArr.size()];
				for(int i=0; i<docArr.size(); i++)  docs[i] = (Doc)docArr.get(i);
				section.setDocs(docs);
			}else{
				docArr = null;
			}
		} catch(Exception e) {
			e.printStackTrace();
			section = null;
			docs = null;
			return section;
		}
		return section;
	}


	//Doc 정보 ///////////////////////////////////////////////////////////////
	private Doc getDocData(Node node) {
		Doc doc = null;
		HashMap attDoc = new HashMap();
		
		try{
			if(node.getNodeType() == Node.ELEMENT_NODE){
				doc = new Doc();

				//id,sim
				if(node.hasAttributes()) {
					attDoc=setAttributes(node);
					doc.setDocData(attDoc);
				}

				//field
				doc = getDocDataSub(doc,node);
			}

		}catch(Exception e){
			e.printStackTrace();
		}

		return doc;
	}

	//Doc field 정보 ////////////////////////////////////////////////////////////
	private Doc getDocDataSub(Doc doc , Node node) {
		try {
			HashMap attDoc=doc.getDocData();
			setAttributes(node, attDoc);
			doc.setDocData(attDoc);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return doc;
	}
}
