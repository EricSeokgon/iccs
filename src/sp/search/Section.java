package sp.search;

import java.util.HashMap;

public class Section {
    
    public HashMap info = new HashMap();
    
    public Doc[] docs;

    public Section(){
    }
    
    public String getName()
    {
        return (String)info.get("name");
    }
    
    public void setInfo(HashMap info){
        this.info= info;
    }
    
    public HashMap getInfo(){
        return info;
    }
    
    public void setDocs(Doc[] docs){
        this.docs = docs;
    }
    
    public String getInfoValue(String name){

        return (String)info.get(name);
    }
    
    public int length(){
        if(isDocs())
            return docs.length;
        else
            return 0;
    }
    
    public boolean isDocs(){
        if(docs == null)
            return false;
        else
            return true;
    }
    
    public String getDocValue(int i, String name){
        return docs[i].getDocValue(name);
    }    
    
}

class Doc {
    private HashMap doc;

    public Doc(){
    }
    public void setDocData(HashMap doc){
        this.doc = doc;
    }

    public HashMap getDocData(){
        return doc;
    }

    public String getDocValue(String name){
        return (String)doc.get(name);

    }
}
