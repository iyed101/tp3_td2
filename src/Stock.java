import java.util.*;

public class Stock {
    private HashSet<Article>articlesMg = new HashSet<Article>() ;
    private HashMap<String, Integer>stockMg = new HashMap<String,Integer>();
    public boolean addNouvArticle(Article a, int qt ){
        if(stockMg.containsKey(a.getNom()))
            return false; 
        articlesMg.add(a) ;
        stockMg.put(a.getNom(), qt);
        return true;
    }
    public boolean verifArticle(String a ){
        return stockMg.containsKey(a);
    }
    public Article getArticle(String a ){
        if(stockMg.containsKey(a))
            for(Article art : articlesMg){
                if(art.getNom().equals(a)){
                    return art;
                }
            }
        return null;
    }
    public boolean removeArticle(String a ){
        if(!stockMg.containsKey(a))
            return false;
        Iterator<Article> it = articlesMg.iterator();
        while(it.hasNext()){
            if (it.next().getNom().equals(a)){ 
                it.remove();
                stockMg.remove(a);
                return true;
            }
        }
        return false;
    }
    public int getQt(String a ){
        if(stockMg.containsKey(a)){
            return stockMg.get(a);
        }
        else{
            return -1;
        }
    }
    public boolean changeQt(String a, int i){
        if(!stockMg.containsKey(a)){
            return false;
        }
        int qt=stockMg.get(a);
        qt=qt+i;
        if (qt<0){
            return false;
        }
        else{
            stockMg.put(a, qt);
            return true;
        }
    }
    public void sortStock(){
        TreeMap<String, Integer> tm = new TreeMap<String, Integer>(stockMg);
        for(String a : tm.keySet()){
            System.out.println("Article de nom "+ a + " de quantité " + tm.get(a));
        }
    }
}
