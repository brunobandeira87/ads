package asltexteditor.editors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.editors.text.FileDocumentProvider;

import asltexteditor.scanner.ASLPartitionScanner;

public class ASLDocumentProvider extends FileDocumentProvider {

	protected IDocument createDocument(Object element) throws CoreException {
		IDocument document = super.createDocument(element);
		if (document != null) {
			IDocumentPartitioner partitioner =
				new FastPartitioner(
					new ASLPartitionScanner(),
					new String[] {
						ASLPartitionScanner.ASL_PLAN,
						//ASLPartitionScanner.ASL_TAG,
						ASLPartitionScanner.ASL_SCOPE,
						ASLPartitionScanner.ASL_COMMENT });
			partitioner.connect(document);
			document.setDocumentPartitioner(partitioner);
		}
		return document;
	}
}