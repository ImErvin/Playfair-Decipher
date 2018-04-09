package ie.gmit.sw.Text;

public class Digrapherator {
	
	
	public Digrapherator() {
		// TODO Auto-generated constructor stub
	}
	
	public String[] diagraphify(String text){
		String diagraphs[] = new String[text.length() / 2];
		
		int j = 0;
		for(int i = 0; i < text.length(); i += 2){
			diagraphs[j] = text.substring(i, i + 2);
	        j++;
		}
		
		return diagraphs;
	}
	
	public String formatify(String text){
		String plainText = text.toUpperCase().replaceAll("[^A-Za-z0-9]", "");
		StringBuffer sb = new StringBuffer(plainText);
		
		for(int i = 0; i < plainText.length() - 1; i += 2){
			if(sb.charAt(i) == sb.charAt(i+1)){
				sb.insert(i+1, "X");
			}
		}
		if(sb.length() % 2 == 1){
			sb.append("X");
		}

		return sb.toString();
	}
	
	public String[] turnDigraphs(String text){
		return this.diagraphify(this.formatify(text));
	}
}
