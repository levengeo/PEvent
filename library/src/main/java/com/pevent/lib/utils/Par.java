
package com.pevent.lib.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public abstract class Par implements Parcelable {
    private static final String TAG =  Par.class.getSimpleName();
    private String key;

    protected Par(String key) {
        this.key = key;
    }

    protected Par(Parcel in) {
        key = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public static class ParInt extends Par {
        private int value;

        public ParInt(String key, int value) {
            super(key);
            this.value = value;
        }

        protected ParInt(Parcel in) {
            super(in);
            value = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(value);
        }

        public static final Creator<ParInt> CREATOR = new Creator<ParInt>() {
            @Override
            public ParInt createFromParcel(Parcel in) {
                return new ParInt(in);
            }

            @Override
            public ParInt[] newArray(int size) {
                return new ParInt[size];
            }
        };

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }


    public static class ParDouble extends Par {
        private double value;

        public ParDouble(String key, double value) {
            super(key);
            this.value = value;
        }

        protected ParDouble(Parcel in) {
            super(in);
            value = in.readDouble();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeDouble(value);
        }

        public static final Creator<ParDouble> CREATOR = new Creator<ParDouble>() {
            @Override
            public ParDouble createFromParcel(Parcel in) {
                return new ParDouble(in);
            }

            @Override
            public ParDouble[] newArray(int size) {
                return new ParDouble[size];
            }
        };

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }
    }


    public static class ParLong extends Par {
        private long value;

        public ParLong(String key, long value) {
            super(key);
            this.value = value;
        }

        protected ParLong(Parcel in) {
            super(in);
            value = in.readLong();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeLong(value);
        }

        public static final Creator<ParLong> CREATOR = new Creator<ParLong>() {
            @Override
            public ParLong createFromParcel(Parcel in) {
                return new ParLong(in);
            }

            @Override
            public ParLong[] newArray(int size) {
                return new ParLong[size];
            }
        };

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }
    }


    public static class ParShort extends Par {
        private short value;

        public ParShort(String key, short value) {
            super(key);
            this.value = value;
        }

        protected ParShort(Parcel in) {
            super(in);
            value = (short) in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(value);
        }

        public static final Creator<ParShort> CREATOR = new Creator<ParShort>() {
            @Override
            public ParShort createFromParcel(Parcel in) {
                return new ParShort(in);
            }

            @Override
            public ParShort[] newArray(int size) {
                return new ParShort[size];
            }
        };

        public short getValue() {
            return value;
        }

        public void setValue(short value) {
            this.value = value;
        }
    }


    public static class ParFloat extends Par {
        private float value;

        public ParFloat(String key, float value) {
            super(key);
            this.value = value;
        }

        protected ParFloat(Parcel in) {
            super(in);
            value = in.readFloat();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeFloat(value);
        }

        public static final Creator<ParFloat> CREATOR = new Creator<ParFloat>() {
            @Override
            public ParFloat createFromParcel(Parcel in) {
                return new ParFloat(in);
            }

            @Override
            public ParFloat[] newArray(int size) {
                return new ParFloat[size];
            }
        };

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }
    }


    public static class ParByte extends Par {
        private byte value;

        public ParByte(String key, byte value) {
            super(key);
            this.value = value;
        }

        protected ParByte(Parcel in) {
            super(in);
            value = in.readByte();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeByte(value);
        }

        public static final Creator<ParByte> CREATOR = new Creator<ParByte>() {
            @Override
            public ParByte createFromParcel(Parcel in) {
                return new ParByte(in);
            }

            @Override
            public ParByte[] newArray(int size) {
                return new ParByte[size];
            }
        };

        public byte getValue() {
            return value;
        }

        public void setValue(byte value) {
            this.value = value;
        }
    }


    public static class ParByteArray extends Par {
        private byte[] value;

        public ParByteArray(String key, byte[] value) {
            super(key);
            this.value = value;
        }

        protected ParByteArray(Parcel in) {
            super(in);
            int length = in.readInt();
            value = new byte[length];
            in.readByteArray(value);
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(value.length);
            dest.writeByteArray(value);
        }

        public static final Creator<ParByteArray> CREATOR = new Creator<ParByteArray>() {
            @Override
            public ParByteArray createFromParcel(Parcel in) {
                return new ParByteArray(in);
            }

            @Override
            public ParByteArray[] newArray(int size) {
                return new ParByteArray[size];
            }
        };

        public byte[] getValue() {
            return value;
        }

        public void setValue(byte[] value) {
            this.value = value;
        }
    }

    public static class ParBoolean extends Par {
        private boolean value;

        public ParBoolean(String key, boolean value) {
            super(key);
            this.value = value;
        }

        protected ParBoolean(Parcel in) {
            super(in);
            value = in.readByte() == 1;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeByte((byte) (value ? 1 : 0));
        }

        public static final Creator<ParBoolean> CREATOR = new Creator<ParBoolean>() {
            @Override
            public ParBoolean createFromParcel(Parcel in) {
                return new ParBoolean(in);
            }

            @Override
            public ParBoolean[] newArray(int size) {
                return new ParBoolean[size];
            }
        };

        public boolean isValue() {
            return value;
        }

        public void setValue(boolean value) {
            this.value = value;
        }
    }

    public static class ParParcelable extends Par {
        private Parcelable value;

        public ParParcelable(String key, Parcelable value) {
            super(key);
            this.value = value;
        }

        protected ParParcelable(String key) {
            super(key);

        }

        protected ParParcelable(Parcel in) throws ClassNotFoundException {
            super(in);
            value = in.readParcelable(Class.forName(getKey()).getClassLoader());
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeParcelable(value, flags);
        }

        public static final Creator<ParParcelable> CREATOR = new Creator<ParParcelable>() {
            @Override
            public ParParcelable createFromParcel(Parcel in) {
//                Log.e(TAG, "createFromParcel");
                try {
                    return new ParParcelable(in);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public ParParcelable[] newArray(int size) {
                return new ParParcelable[size];
            }
        };

        public Parcelable getValue() {
            return value;
        }

        public void setValue(Parcelable value) {
            this.value = value;
        }
    }


    public static class ParString extends Par {
        private String value;

        public ParString(String key, String value) {
            super(key);
            this.value = value;
        }

        protected ParString(String key) {
            super(key);

        }

        protected ParString(Parcel in) throws ClassNotFoundException {
            super(in);
            value = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeString(value);
        }

        public static final Creator<ParString> CREATOR = new Creator<ParString>() {
            @Override
            public ParString createFromParcel(Parcel in) {
                try {
                    return new ParString(in);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public ParString[] newArray(int size) {
                return new ParString[size];
            }
        };

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


    public static class ParSerializable extends Par {
        private Serializable value;

        public ParSerializable(String key, Serializable value) {
            super(key);
            this.value = value;
        }

        protected ParSerializable(String key) {
            super(key);

        }

        protected ParSerializable(Parcel in) throws ClassNotFoundException {
            super(in);
            value = in.readSerializable();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeSerializable(value);
        }

        public static final Creator<ParSerializable> CREATOR = new Creator<ParSerializable>() {
            @Override
            public ParSerializable createFromParcel(Parcel in) {
                try {
                    return new ParSerializable(in);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public ParSerializable[] newArray(int size) {
                return new ParSerializable[size];
            }
        };

        public Serializable getValue() {
            return value;
        }

        public void setValue(Serializable value) {
            this.value = value;
        }
    }

}



