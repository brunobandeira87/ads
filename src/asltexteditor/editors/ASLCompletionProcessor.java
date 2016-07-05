package asltexteditor.editors;

import java.util.ArrayList;
import java.util.List;
import java.text.MessageFormat;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.ContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationPresenter;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.swt.graphics.Point;

public class ASLCompletionProcessor implements IContentAssistProcessor {
	protected static class Validator implements IContextInformationValidator, IContextInformationPresenter {

		protected int fInstallOffset;

		public boolean isContextInformationValid(int offset) {
			return Math.abs(fInstallOffset - offset) < 5;
		}

		public void install(IContextInformation info, ITextViewer viewer, int offset) {
			fInstallOffset= offset;
		}

		public boolean updatePresentation(int documentPosition, TextPresentation presentation) {
			return false;
		}
	}

	protected final static String[] completion =
		{ "plano : regra() <- acao().", "definicao(inicial).", "regra()", "acao()", "true", "false" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$s

	protected IContextInformationValidator fValidator= new Validator();

	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int documentOffset) {
		ICompletionProposal[] result= new ICompletionProposal[completion.length];
		for (int i= 0; i < completion.length; i++) {
			IContextInformation info= new ContextInformation(completion[i], MessageFormat.format("{0} - characters around insertion point", new Object[] { completion[i] })); 
			result[i]= new CompletionProposal(completion[i], documentOffset, 0, completion[i].length(), null, completion[i], info, MessageFormat.format("Keyword: {0}", new Object[] { completion[i]}));
		}
		return result;
	}

	public IContextInformation[] computeContextInformation(ITextViewer viewer, int documentOffset) {
		IContextInformation[] result= new IContextInformation[5];
		for (int i= 0; i < result.length; i++)
			result[i]= new ContextInformation(
				MessageFormat.format("proposal {0} at position {1}", new Object[] { new Integer(i), new Integer(documentOffset) }),  //$NON-NLS-1$
				MessageFormat.format("proposal {0} valid from {1} to {2}", new Object[] { new Integer(i), new Integer(documentOffset - 5), new Integer(documentOffset + 5)})); //$NON-NLS-1$
		return result;
	}

	public char[] getCompletionProposalAutoActivationCharacters() {
		char[] ret = {'+', '-', '+', '?'};
		return ret;
	}

	public char[] getContextInformationAutoActivationCharacters() {
		return new char[] { '#' };
	}

	public IContextInformationValidator getContextInformationValidator() {
		return fValidator;
	}

	public String getErrorMessage() {
		return null;
	}
	
	
	
	/*
	@Override
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
		// Busca o documento atual
		IDocument currentDocument = viewer.getDocument();
		
		//  Retrieve current selection range
		
		Point selectedRange = viewer.getSelectedRange();
		// instance for collecting the generated ICompletionProposal instance
		List propList = new ArrayList();
		
		return null;
	}

	@Override
	public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public char[] getCompletionProposalAutoActivationCharacters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public char[] getContextInformationAutoActivationCharacters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IContextInformationValidator getContextInformationValidator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	*/

}
