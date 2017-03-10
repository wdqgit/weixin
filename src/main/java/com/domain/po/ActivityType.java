package com.domain.po;

public enum ActivityType {
	RUN {
		public String getName() {
			return "进行中";
		}
	},
	END {
		public String getName() {
			return "结束了";
		}
	};
	public abstract String getName();
}
