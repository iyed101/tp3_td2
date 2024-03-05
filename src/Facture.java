import java.util.ArrayList;

public class Facture {
    private ArrayList<LigneFacture> lignes;
    private Stock stock;
    public Facture(Stock stock){
        this.stock = stock;
        this.lignes = new ArrayList<LigneFacture>();
    }
    public boolean addLigne(int qt,String a){
        if(!stock.verifArticle(a)){
            return false;
        }
        int q=stock.getQt(a);
        if(q<=0 || q<qt){
            return false;
        }
        int id=lignes.size()+1;
        LigneFacture l = new LigneFacture(id,qt,stock.getArticle(a));
        lignes.add(l);
        stock.changeQt(a, -qt);
        return true;
    }
    public boolean removeLigne(int id){
        int size=lignes.size();
        if (id>size || id<1){
            return false;
        }
        else{
            LigneFacture l = lignes.get(id-1);
            int qt = l.getQt();
            stock.changeQt(l.getArticle().getNom(), qt);
            lignes.remove(id-1);
            for(int i=id-1;i<size-1;){
                lignes.get(i).setId(i+1);
                return true;
            }
        }
        return false;
    }
    public double getMontantTotal(){
        double total=0;
        for(LigneFacture l : lignes){
            total+=l.getMontantTotal();
        }
        return total;
    }
    public static void main(String[] args) {
        Article article = new Article("test", 10.0);
        Article article2 = new Article("test2", 15.0);
        Article article3 = new Article("test3", 20.0);
        Article article4 = new Article("test4", 30.0);
        Stock stock = new Stock();
        Facture f = new Facture(stock);
        stock.addNouvArticle(article, 10);
        stock.addNouvArticle(article2, 5);
        stock.addNouvArticle(article3, 3);
        stock.addNouvArticle(article4, 2);
        stock.sortStock();
        f.addLigne(10,"test");
        f.addLigne(5,"test2");
        f.addLigne(3,"test3");
        System.out.println(stock.verifArticle("test"));
        System.out.println(stock.getArticle("test"));
        System.out.println(stock.removeArticle("test4"));
        System.out.println(stock.getQt("test2"));
        System.out.println(stock.changeQt("test2", 15));
        System.out.println(f.removeLigne(2));
        System.out.println(f.getMontantTotal());
    }
}