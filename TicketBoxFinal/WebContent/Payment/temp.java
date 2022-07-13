
String seatArr[] = {"1층C구역06열22번 VIP",  "1층C구역06열23번 VIP"};

int total_pay = 0;
		
		for(int i=0;i<seatArr.length;i++) {
			
			char[] arr = seatArr[i].toCharArray();	
			
			for(int j=0;j<arr.length;j++) { 
				if(arr[j] == 'v') {
				 total_pay+=150000;	
				}
				else if(arr[j] == 'r') {
					total_pay +=130000;
				}
				else if(arr[j] =='s') {
					total_pay += 100000;
				}
			}
		}