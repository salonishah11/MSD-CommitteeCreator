
public interface CandidateInterface {
		
	// function to view candidate details
	public void viewCandidateProfile(Object candidate);
		
	// get candidate details
	public Object getCandidateDetails();
	
	// add candidate to the committe
	public void addCandidateToCommittee(Object candidate);
	
	// contact candidate after selecting
	public void contactCandidate(Object candidate);
}
