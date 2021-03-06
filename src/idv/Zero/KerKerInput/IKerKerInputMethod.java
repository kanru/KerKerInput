package idv.Zero.KerKerInput;

import android.view.View;

/**
 * This class provides the basic implementation for a method runs
 * in KerKerInput. Any IME should extend this class and implements
 * all the abstract methods or override the existing method to customize
 * the behaviors.
 * 
 * @author Zero
 *
 */
public abstract class IKerKerInputMethod {
	protected KerKerInputCore _core;
	
	/* Input Method Core */
	public void initInputMethod(KerKerInputCore core) { _core = core; }
	abstract public void onEnterInputMethod();
	public void onLostFocus()
	{
		_core.clearCandidates();
		_core.hideCandidatesView();
	}
	abstract public void onLeaveInputMethod();
	abstract public String getName();
	abstract public void commitCurrentComposingBuffer();
	public boolean shouldAvailableForSwitchingButton() { return true; }
	public boolean hasCustomInputView() { return false; }
	public View onCreateInputView() { return null; }

	/* Keyboard Events */
	public Keyboard getDesiredKeyboard() { return null; }
	public boolean wantHandleEvent(int keyCode) { return (keyCode > -2) || (keyCode == Keyboard.KEYCODE_DELETE) ; }
	abstract public boolean onKeyEvent(int keyCode, int[] keyCodes);
	abstract public void commitCandidate(int currentCandidate);
	public void onTextEvent(CharSequence text) { if (_core != null) _core.getConnection().commitText(text, 1); }
	
	/* Candidates Handling */
	abstract public void setTotalPages(int totalPages);
	abstract public void setCurrentPage(int currentPage);
}
