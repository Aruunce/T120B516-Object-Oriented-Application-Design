package clientSide;

/*
 * Protocol.java
 *
 * Created on 25, 2008, 10:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
public class Protocol {
    
    
    /**
     * Creates a new instance of Protocol
     */
    private String message="";
    public Protocol() 
    {
        
    }
    
    public String RegisterPacket(int x,int y)
    {
        message="Hello"+x+","+y;
        return message;
    }
    public String UpdatePacket(int x,int y,int id,int dir)
    {
        message="Update"+x+","+y+"-"+dir+"|"+id;
        return message;
    }
    public String ShotPacket(int id)
    {
        message="Shot"+id;
        return message;
    }
    
    public String HitPacket(int id)
    {
        message="Hit"+id;
        return message;
    }
    
    public String RemoveClientPacket(int id)
    {
        message="Remove"+id;
        return message;
    }
    public String ExitMessagePacket(int id)
    {
        message="Exit"+id;
        return message;
    }
    public String MapSelectionPacket(int mapIndex) {
        message = "MapSelect:" + mapIndex;
        return message;
    }
    
    public String DestroyObstaclePacket(int obstacleId) {
        message="DestroyObstacle:" + obstacleId;
        return message;
    }
    
    public String InitialMapIndexPacket(int mapIndex) {
        message = "InitialMapIndex:" + mapIndex;
        return message;
    }
    
    public String ChangeGameState(String state) {
        message = "ChangeGameState:" + state;
        return message;
    }
}
