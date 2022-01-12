package eportfolio; 
public class Stock extends Investment
{
    private String symbol; 
    private String name;
    private int quantity; 
    private double price; 
    private double bookvalue; 

    public Stock() 
    {

    }

    public String getSymbol()
    {
        return this.symbol;
    }

    public String getName()
    {
        return this.name;
    }

    public double getPrice()
    {
        return this.price;
    }

    public int getQuantity()
    {
        return this.quantity;
    }


    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setName(String theName)
    {
        this.name=theName;
    }

    public void setQuantity(int theQuantity)
    {
        this.quantity=theQuantity;
    }

    public void setPrice(double thePrice)
    {
        this.price=thePrice;
    }

    public void setBookvalue(int theQuantity, double thePrice)
    {
        this.bookvalue= this.bookvalue + ( (double)theQuantity*thePrice + 9.99);   
    }

    public void saveBookvalue(double theBookvalue)
    {
        this.bookvalue= theBookvalue;    
    }

    public void sell(int theQuantity, double thePrice)
    {
        double payment=theQuantity*thePrice -9.99;
        double gain=payment-(this.bookvalue*(double)(theQuantity/this.quantity)); 
        System.out.println("Payment=" + payment);
        System.out.println("Gain=" + gain);
        this.bookvalue= this.bookvalue-(this.bookvalue*(double)(theQuantity/this.quantity));
        this.quantity=this.quantity-theQuantity; 
    }

    public double getGain()
    {
        double payment= ((double)this.quantity)*this.price -9.99; 
        double gain=payment-this.bookvalue;
        return gain;
    }

    public void print()
    {
        System.out.println("Symbol: " + this.symbol + " Name: " + this.name + " Quantity: " + this.quantity + "  Price: " + this.price);
    }

}