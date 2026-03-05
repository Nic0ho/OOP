package ex02;

public class ViewableResult implements Viewable
{
    public View getView()
    { return new ViewResult(); }
}
