package com.camark.enumstudy;

public enum MyEnum {
    RED(5) {
        @Override
        public MyEnum next() {
            return YELLOW;
        }
    },
    YELLOW(2) {
        @Override
        public MyEnum next() {
            return RED;
        }

    },
    GREEN(3) {
        @Override
        public MyEnum next() {
            return YELLOW;
        }

    };

    private MyEnum(int time) {
        mTime = time;
    }

    private int mTime;

    public int getTime() {
        return mTime;
    }

    public abstract  MyEnum next();
}
