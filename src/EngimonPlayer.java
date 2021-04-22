
public class EngimonPlayer extends Engimon {
    Player plyr;
    Direction direction;

    public EngimonPlayer(Coordinate bound, Player plyr) {
        this(bound, false, plyr);
        
    }
    
    public EngimonPlayer(Coordinate bound, boolean active, Player plyr) {
        super(bound, 3);
        this.active = active;
        this.level = 0;
        this.species = "";
        this.element = Element.Ground;
        this.skill = new ArrayList<Skill>();
        
        this.plyr = plyr;
        try {
            this.setCo(this.plyr.getX()-1, this.plyr.getY());
        } catch (CoordinateMapOutOFBoundException e) {
            e.printStackTrace();
        }
        this.direction = Direction.right;
    }
    
    public void followPlayer() {
        if (this.getActive() == true) {
            try {
                switch (direction){
                    case up:
                        this.MoveUp();
                        break;
                        case down:
                        this.MoveDown();
                        break;
                    case left:
                        this.MoveLeft();
                        break;
                    case right:
                        this.MoveRight();
                        break;
                }
                int x_rel = this.plyr.getX() - this.getX();
                if (x_rel == 1) {
                    this.direction = Direction.right;
                } else if (x_rel == -1) {
                    this.direction = Direction.left;
                }
                int y_rel = this.plyr.getY() - this.getY();
                if (y_rel == 1) {
                    this.direction = Direction.down;
                } else if (y_rel == -1) {
                    this.direction = Direction.up;
                }
            } catch (CoordinateMapOutOFBoundException e) {
                
            }
        }
    }

    public void startFollowPlayer(int x, int y) {
        try {
            this.setCo(x, y);
            
        } catch (CoordinateMapOutOFBoundException e) {
            e.printStackTrace();
        }
    }

    public void MoveUp() throws CoordinateMapOutOFBoundException {
        try {
            setCo(this.getX(), this.getY()-1);
        } catch (CoordinateMapOutOFBoundException e) {
            throw new CoordinateMapOutOFBoundException(this.getBound());
        }
    }
    public void MoveDown() throws CoordinateMapOutOFBoundException {
        try {
            setCo(this.getX(), this.getY()+1);
        } catch (CoordinateMapOutOFBoundException e) {
            throw new CoordinateMapOutOFBoundException(this.getBound());
        }
    }
    public void MoveLeft() throws CoordinateMapOutOFBoundException {
        try {
            setCo(this.getX()-1, this.getY());
        } catch (CoordinateMapOutOFBoundException e) {
            throw new CoordinateMapOutOFBoundException(this.getBound());
        }
    }
    public void MoveRight() throws CoordinateMapOutOFBoundException {
        try {
            setCo(this.getX()+1, this.getY());
        } catch (CoordinateMapOutOFBoundException e) {
            throw new CoordinateMapOutOFBoundException(this.getBound());
        }
    }

};
