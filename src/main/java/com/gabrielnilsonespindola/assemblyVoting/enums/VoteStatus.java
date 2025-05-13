package com.gabrielnilsonespindola.assemblyVoting.enums;

public enum VoteStatus {

	YES(1), NO(2);

	private int code;

	private VoteStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static VoteStatus valueOf(int code) {
		for (VoteStatus value : VoteStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}

		throw new IllegalArgumentException("Invalid Vote");
	}

}
