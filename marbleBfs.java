import java.util.LinkedList;
import java.util.Queue;

public class marbleBfs{

    /*
     * 스타트링크에서 판매하는 어린이용 장난감 중에서 가장 인기가 많은 제품은 구슬 탈출이다. 
     * 구슬 탈출은 직사각형 보드에 빨간 구슬과 파란 구슬을 하나씩 넣은 다음, 빨간 구슬을 구멍을 통해 빼내는 게임이다.
보드의 세로 크기는 N, 가로 크기는 M이고, 편의상 1×1크기의 칸으로 나누어져 있다. 
가장 바깥 행과 열은 모두 막혀져 있고, 보드에는 구멍이 하나 있다. 빨간 구슬과 파란 구슬의 크기는 보드에서 1×1크기의 칸을 가득 채우는 사이즈이고, 각각 하나씩 들어가 있다. 
게임의 목표는 빨간 구슬을 구멍을 통해서 빼내는 것이다. 이때, 파란 구슬이 구멍에 들어가면 안 된다.
이때, 구슬을 손으로 건드릴 수는 없고, 중력을 이용해서 이리 저리 굴려야 한다. 
왼쪽으로 기울이기, 오른쪽으로 기울이기, 위쪽으로 기울이기, 아래쪽으로 기울이기와 같은 네 가지 동작이 가능하다.
각각의 동작에서 공은 동시에 움직인다. 
빨간 구슬이 구멍에 빠지면 성공이지만, 파란 구슬이 구멍에 빠지면 실패이다. 빨간 구슬과 파란 구슬이 동시에 구멍에 빠져도 실패이다. 
빨간 구슬과 파란 구슬은 동시에 같은 칸에 있을 수 없다. 또, 빨간 구슬과 파란 구슬의 크기는 한 칸을 모두 차지한다. 기울이는 동작을 그만하는 것은 더 이상 구슬이 움직이지 않을 때 까지이다.
보드의 상태가 주어졌을 때, 최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지 구하는 프로그램을 작성하시오.
첫 번째 줄에는 보드의 세로, 가로 크기를 의미하는 두 정수 N, M (3 ≤ N, M ≤ 10)이 주어진다. 
다음 N개의 줄에 보드의 모양을 나타내는 길이 M의 문자열이 주어진다. 

이 문자열은 '.', '#', 'O', 'R', 'B' 로 이루어져 있다. 
'.'은 빈 칸을 의미하고, 
'#'은 공이 이동할 수 없는 장애물 또는 벽을 의미하며, 
'O'는 구멍의 위치를 의미한다. 
'R'은 빨간 구슬의 위치, 
'B'는 파란 구슬의 위치이다.
입력되는 모든 보드의 가장자리에는 모두 '#'이 있다. 
구멍의 개수는 한 개 이며, 빨간 구슬과 파란 구슬은 항상 1개가 주어진다.


5 5
#####
#..B#
#.#.#
#RO.#
#####
     */
    public static char[][]arr1={
        {'#','#','#','#','#'},
        {'#','.','.','B','#'},
        {'#','.','#','.','#'},
        {'#','R','O','.','#'},
        {'#','#','#','#','#'}
    };

    public static char[][]arr2={
        {'#','#','#','#','#','#','#'},
        {'#','.','.','R','#','B','#'},
        {'#','.','#','#','#','#','#'},
        {'#','.','.','.','.','.','#'},
        {'#','#','#','#','#','.','#'},
        {'#','O','.','.','.','.','#'},
        {'#','#','#','#','#','#','#'}
    };

    public static int SIZE =7;


    class Pair{
        marble red = new marble();
        marble blue = new marble();
        int cnt=0;
    }

    class marble{
        int X;
        int Y;

        marble()
        {
            X=0;
            Y=0;
        }
    }

    int test(char [][]arr)
    {
        /* 홀 위치 */
        int hX=0;
        int hY=0;
        
        /* 좌표이동
         * 좌 -1, 0
         * 하 0, 1
         * 우 1, 0
         * 상 0 -1
         */
        int []dirX = {-1,0,1,0};
        int []dirY = {0,1,0,-1};

        /* 이동여부 */
        boolean[][][][] check = new boolean[SIZE][SIZE][SIZE][SIZE];

        Pair p = new Pair();
        
        /* 구슬위치와 홀 찾기 */
        for(int i=0; i<arr.length; i++)
        {
            for(int j=0; j<arr[i].length; j++)
            {
                if( 'B' == arr[i][j])
                {
                    p.blue.Y = i;
                    p.blue.X = j;
                }
                if( 'R' == arr[i][j])
                {
                    p.red.Y = i;
                    p.red.X = j;
                }
                if( 'O' == arr[i][j])
                {
                    hY = i;
                    hX = j;
                }
            }
        }
        System.out.println("blue=" + p.blue.Y + p.blue.X);
        System.out.println("red=" + p.red.Y + p.red.X);
        System.out.println("hall=" + hY + hX);

        Queue<Pair>que = new LinkedList<>();
        
        que.add(p);

        while(!que.isEmpty())
        {
            boolean isRedHall = false;
            boolean isBlueHall = false;

            Pair curP = que.poll();
            
            check[curP.red.X][curP.red.Y][curP.blue.X][curP.blue.Y] = true;

            /* 10회 이상 이동은 패스  */
            if( curP.cnt > 10 )
            {
                continue;
            }
            /*상 하 좌 우 이동이 필요하니 4번 */
            for(int i=0; i<4; i++)
            {
                int newRX = curP.red.X;
                int newRY = curP.red.Y;
                int newBX = curP.blue.X;
                int newBY = curP.blue.Y;
                
                /* 빨간구슬 이동하면서 #만날때까지 이동 */
                while( arr[newRY + dirY[i]][newRX + dirX[i]] != '#')
                {
                    newRX += dirX[i];
                    newRY += dirY[i];

                    if( hX == newRX && hY == newRY)
                    {
                        isRedHall = true;
                        break;
                    }
                }
                /* 블루구슬 이동하면서 #만날때까지 이동 */
                while( arr[newBY + dirY[i]][newBX + dirX[i]] != '#')
                {
                    newBX += dirX[i];
                    newBY += dirY[i];

                    if( hX == newBX && hY == newBY)
                    {
                        isBlueHall = true;
                        break;
                    }
                }

                /* 블루구슬이 만약 빠졌다면 처음부터 */
                if( isBlueHall ) continue;

                /* 빨간구슬만 빠졌으면 */
                if( isRedHall ) return curP.cnt+1;

                /* 구슬이 안빠졌는데 서로 위치가 같으면 ? */
                if( newRX == newBX && newRY == newBY )
                {
                    switch (i) {
                        case 0:
                        /*좌 */
                            if( curP.red.X > curP.blue.X ) newRX -= dirX[i];
                            else newBX -= dirX[i];
                            break;
                        case 1:
                        /*하 */
                            if( curP.red.Y > curP.blue.Y ) newRY -= dirY[i];
                            else newBY -= dirY[i];
                            break;
                        case 2:
                        /*우 */
                            if( curP.red.X < curP.blue.X ) newRX -= dirX[i];
                            else newBX -= dirX[i];
                            break;
                        case 3:
                        /*상 */
                            if( curP.red.Y < curP.blue.Y ) newRY -= dirY[i];
                            else newBY -= dirY[i];
                            break;
                
                        default:
                            break;
                    } 
                }

                /* 처음방문이면 que 추가 */
                if( check[newRX][newRY][newBX][newBY] == false )
                {
                    Pair newP = new Pair();
                    newP.red.X = newRX;
                    newP.red.Y = newRY;
                    newP.blue.X = newBX;
                    newP.blue.Y = newBY;
                    newP.cnt = curP.cnt+1;
                    
                    que.add(newP);
                }
            }

        }
        return -1;

    }

    public static void main(String[] args) {
        marbleBfs t = new marbleBfs();
        System.out.println( "Count = " + t.test(arr2));
   }
}