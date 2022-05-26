package assignment8;

public enum State {
	NOTHING_LOADED {
	}, SORT_FILE_LOADED {
		
		@Override
		public boolean leftButtonEnabled(int index) {
			return true;
		}
		
		@Override
		public State loadSearchFile() {
			return BOTH_FILES_LOADED;
		}
	}, SEARCH_FILE_LOADED {
		@Override
		public State loadSortFile() {
			return BOTH_FILES_LOADED;
		}
	}, BOTH_FILES_LOADED {
		@Override
		public boolean leftButtonEnabled(int index) {
			return true;
		}
		@Override
		public boolean rightButtonEnabled(int index) {
			return leftButtonClicked[index];
		}
		
		@Override
		public State loadSearchFile() {
			return this;
		}
		@Override
		public State loadSortFile() {
			return this;
		}
	};
	
	static private boolean[] leftButtonClicked = new boolean[assignment8.NUM_BUTTONS];
	static void setLeftButtonClicked(int index, boolean value) {
		leftButtonClicked[index] = value;
	}
	public State reset() {
		for (int i = 0; i < leftButtonClicked.length; i++) {
			leftButtonClicked[i] = false;
		}
		return NOTHING_LOADED;
		}
		
	public State loadSearchFile() {
		return SEARCH_FILE_LOADED;
	}
	public State loadSortFile() {
		return SORT_FILE_LOADED;
	}
	public boolean leftButtonEnabled(int index) {
		return false;
	}
	public boolean rightButtonEnabled(int index) {
		return false;
	}
	
	
}
