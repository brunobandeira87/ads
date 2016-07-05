package asltexteditor.editors;
import asltexteditor.scanner.ASLPartitionScanner;
import asltexteditor.scanner.ASLPlanScanner;
import asltexteditor.scanner.ASLScanner;
import asltexteditor.scanner.ASLScopeScanner;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.graphics.RGB;



public class ASLSourceViewerConfiguration extends SourceViewerConfiguration {
	private ASLDoubleClickStrategy doubleClickStrategy;	
	private ASLScanner scanner;
	private ASLPlanScanner planScanner;
	private ASLScopeScanner scopeScanner;
	private ColorManager colorManager;

	public ASLSourceViewerConfiguration(ColorManager colorManager) {
		this.colorManager = colorManager;
	}
	
	
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			ASLPartitionScanner.ASL_SCOPE,
			ASLPartitionScanner.ASL_COMMENT,
			ASLPartitionScanner.ASL_PLAN
			/*,
			ASLPartitionScanner.ASL_TAG */ };
	}
	
	
	public ITextDoubleClickStrategy getDoubleClickStrategy(
		ISourceViewer sourceViewer,
		String contentType) {
		if (doubleClickStrategy == null)
			doubleClickStrategy = new ASLDoubleClickStrategy();
		return doubleClickStrategy;
	}

	protected ASLScanner getASLScanner() {
		if (scanner == null) {
			scanner = new ASLScanner(colorManager);
			scanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(IASLColorConstants.DEFAULT))));
		}
		return scanner;
	}
/*	
	
 */
	protected ASLPlanScanner getASLPlanScanner(){
		if(planScanner == null){
			planScanner = new ASLPlanScanner(colorManager);
			planScanner.setDefaultReturnToken(new Token(new TextAttribute(colorManager.getColor(IASLColorConstants.TAG))));
		}
		return planScanner;
	}
	protected ASLScopeScanner getASLScopeScanner() {
		if (scopeScanner == null) {
			scopeScanner = new ASLScopeScanner(colorManager);
			scopeScanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(IASLColorConstants.ASL_SCOPE))));
		}
		return scopeScanner;
	}

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr =
			new DefaultDamagerRepairer(getASLScopeScanner());
		
		reconciler.setDamager(dr, ASLPartitionScanner.ASL_SCOPE);
		reconciler.setRepairer(dr, ASLPartitionScanner.ASL_SCOPE);
		dr = new DefaultDamagerRepairer(getASLPlanScanner());
		reconciler.setDamager(dr, ASLPartitionScanner.ASL_PLAN);
		reconciler.setRepairer(dr, ASLPartitionScanner.ASL_PLAN);
		
		dr = new DefaultDamagerRepairer(getASLScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		NonRuleBasedDamagerRepairer ndr =
			new NonRuleBasedDamagerRepairer(
				new TextAttribute(
					colorManager.getColor(IASLColorConstants.ASL_COMMENT)));
		reconciler.setDamager(ndr, ASLPartitionScanner.ASL_COMMENT);
		reconciler.setRepairer(ndr, ASLPartitionScanner.ASL_COMMENT);

		return reconciler;
	}
	@Override
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		// TODO Auto-generated method stub
		ContentAssistant assistant = new ContentAssistant();
		
		IContentAssistProcessor processor = new ASLCompletionProcessor();
		
		assistant.setContentAssistProcessor(processor, ASLPartitionScanner.ASL_COMMENT);
		
		
		assistant.enableAutoActivation(true);
		assistant.setAutoActivationDelay(500);
		assistant.setProposalPopupOrientation(IContentAssistant.PROPOSAL_OVERLAY);
		assistant.setContextInformationPopupOrientation(IContentAssistant.CONTEXT_INFO_ABOVE);
		assistant.setContextInformationPopupBackground(colorManager.getColor(new RGB(150, 150, 0)));
		
		
		return super.getContentAssistant(sourceViewer);
	}
	
	

}