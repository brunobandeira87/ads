package asltexteditor.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class ASLEditor extends TextEditor {

	/**
	 * SourceViewerConfiguraton => used to add additional features to the editor's user interface.
	 * IDocumentProvider => encapsulates the mechanism for creatng a JFace Text representation of the doocument
	 * 						being edited from its source.
	 * 
	 *  IDocument => contains the text of your document, and can be used to query its structure. It can also 
	 *  			 be used to mark for position in the document
	 */
	
	private ColorManager colorManager;

	public ASLEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new ASLConfiguration(colorManager));
		setDocumentProvider(new ASLDocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
