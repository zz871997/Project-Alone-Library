package tranquangkhai20152005.library.model;

import java.util.ArrayList;

public interface LoanBookDAO {
	public ArrayList<LoanBook> getAllLoanBooks();
	public LoanBook getLoanBook(String maMT);
	
	public void insertLoanBook(LoanBook loanBook);
}
