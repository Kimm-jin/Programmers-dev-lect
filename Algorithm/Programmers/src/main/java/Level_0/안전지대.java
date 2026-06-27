package Level_0;

import java.util.*;

class Solution {
    // BFS
    public int solution(int[][] board) {
        Queue<int[]> q = new LinkedList<>();
        int n = board.length;
        boolean visited[][] = new boolean[n][n];
        int dy[] = {1,-1,0,0,1,1,-1,-1};
        int dx[] = {0,0,-1,1,-1,1,1,-1};

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(board[i][j]==1){
                    visited[i][j]=true;
                    q.add(new int[]{i,j});
                }
            }
        }

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];
            for(int i=0; i<8; i++){
                int ny = y+dy[i];
                int nx = x +dx[i];
                if(ny<0||nx<0||ny>=n||nx>=n || visited[ny][nx])continue;
                visited[ny][nx]=true;
            }
        }
        int ans=0;
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                if(!visited[i][j])ans++;
        return ans;
    }
11
/* ------------------------------------------------------------- */
    // bruteforce
    public int solution2(int[][] board) {
        int n = board.length;
        int ans=0;
        boolean visited[][] = new boolean[n][n];
        int dy[] = {1, -1, 0, 0, 1, 1, -1, -1};
        int dx[] = {0, 0, -1, 1, -1, 1, 1, -1};
//        int move[] = {-1,0,1}; // 자신 포함 9칸 탐색

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) {
                    visited[i][j] = true;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j]==1){
                    for (int k = 0; k < 8; k++) {
                        int ny = i+dy[k];
                        int nx = j+dx[k];
                        if(ny<0||nx<0||ny>=n||nx>=n)continue;
                        visited[ny][nx]=true;
                    }
//                    자신 포함 9칸 탐색
//                    for (int dy : move) {
//                        for (int dx : move) {
//                            int ny = y + dy;
//                            int nx = x + dx;
//
//                            if (ny < 0 || ny >= n || nx < 0 || nx >= n) continue;
//
//                            visited[ny][nx] = true;
//                        }
//                    }
                }
            }
        }

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (!visited[i][j]) ans++;
        return ans;
    }
}


// board[y][x] 기준으로 좌표를 관리한다.
// 폭탄 위치와 주변 8칸을 위험지대로 표시한다.
// 주변 칸은 다시 큐에 넣지 않는다. 이 문제는 BFS 확산이 아니라 1칸 마킹 문제다.
// 확산이 아니기 떄문에 brutefoce + 방향벡터로 해결 가능하다..