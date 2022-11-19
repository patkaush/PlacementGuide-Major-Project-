package bodyReqRes;

import java.util.HashMap;
import java.util.HashSet;

public class SetInterestReqBody {
  private HashSet<Integer> interests;
  private HashMap<Integer,IntrValId> values;
  //returns valuess
  public HashMap<Integer,IntrValId> getValues() {
	return values;
}

public void setValues(HashMap<Integer,IntrValId> values) {
	this.values = values;
}

public HashSet<Integer> getInterests() {
    return this.interests;
  }
  
  public void setInterests(HashSet<Integer> interests) {
    this.interests = interests;
  }
}
