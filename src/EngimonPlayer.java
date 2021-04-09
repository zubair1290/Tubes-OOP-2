
public class EngimonPlayer extends Engimon {
    Player plyr;
    Direction direction;

    EngimonPlayer(Player plyr) {
        this(false, plyr);
    }
    
    EngimonPlayer(boolean active, Player plyr) {
        super(active);
        this.plyr = plyr;
        this.setCo(this.plyr.getX()-1, this.plyr.getY());
        this.direction = Direction.right;
    }
    
    public void followPlayer() {
        if (this.getActive() == true) {
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
        }
    }

    public void startFollowPlayer(int x, int y) {
        this.setCo(x, y);
    }

};
