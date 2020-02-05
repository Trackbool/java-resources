import java.util.HashSet;
import java.util.Set;

public class SecretWord {
    private final String value;
    private String secretValue;
    private final Set<Character> guessedCharacters = new HashSet<>();
    
    public SecretWord(String value) {
        this.value = value;
        this.setUpSecretValue();
    }
    
    public String get() {
        return this.secretValue;
    }
    
    public boolean guessLetter(Character letter) {
        boolean guessed = this.value.contains(letter.toString());
        if(guessed) {
            this.guessedCharacters.add(letter);
            this.setUpSecretValue();
        }
        
        return guessed;
    }
    
    public boolean isGuessed() {
        String secretValueToCompare = this.secretValue.replace(" ", "");
        
        return this.value.equals(secretValueToCompare);
    }
    
    private void setUpSecretValue() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.value.length(); i++) {
            char currentChar = this.value.charAt(i);
            if(guessedCharacters.contains(currentChar)) {
                sb.append(currentChar);
            } else {
                sb.append("_");
            }
            sb.append(" ");
        }
        
        this.secretValue = sb.deleteCharAt(sb.length() - 1).toString();
    }
}
