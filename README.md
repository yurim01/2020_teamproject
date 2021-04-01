# Closest pair of points
### class closet에 대한 설명사

```javascript
static class Point {
		int x;
		int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
```

위의 코드는 x와 y의 위치(좌표)를 설정하는 코드이다.


```javascript
static int getdistancepow(Point p1, Point p2) {
		return (p1.x-p2.x)*(p1.x-p2.x) + (p1.y-p2.y)*(p1.y-p2.y);
	}
```

두 점을 입력 받았을 떄 주 점 사이의 거리를 구하는 함수이다.


```javascript
static int bruteforce(int left, int right)
```

위의 함수는 i<=3 일 경우에 사용될 완전 탐색이다.

```javascript
static int divcon(int left, int right)
```

closet을 하기 위한 함수이다. 입력으로 처음과 끝을 입력받는다.

```javascript
int size = right - left +1;
```

그리고 그것을 이용해 크기를 구한다.

```javascript
int mid = (left + right) /2;
int d_s_left = divcon(left,mid);
int d_s_right = divcon(mid+1,right);
```

중간 위치를 구하고  그 기준으로 왼쪽 구역과 오른쪽 구역을 나눈다.

```javascript
int d_min = Math.min(d_s_left, d_s_right);
```

왼쪽구역과 오른쪽구역에서 구한d의 값 중 작은값 찾는다.

```javascript
List<Point> s_mid = new ArrayList<>(); 
		for(int i = left; i<= right; i++) {
			int d_x = p[i].x - p[mid].x;
			if(d_x*d_x < d_min)
				s_mid.add(p[i]); 
		}
```

위의 과정을 통해 리스트에 추가한다.

```javascript
Collections.sort(s_mid, (p1,p2) -> p1.y - p2.y); 
		}
```

추가한 리스트들을 y축 좌표 기준 오름차순 정렬한다.

```javascript
for(int i =0; i< c_size -1;i++) {
			for(int j = i+1; j<c_size; j++) {
				int d_y = s_mid.get(j).y - s_mid.get(i).y;
				if(d_y*d_y < d_min) {
					int d = getdistancepow(s_mid.get(i), s_mid.get(j));
					if(d_min > d) d_min = d;
				}
				else break;
			}
		}
		return d_min;
```

마지막으로 최소 값을 내보낸다.
