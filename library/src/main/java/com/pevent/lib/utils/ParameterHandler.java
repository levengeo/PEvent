package com.pevent.lib.utils;

import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;

import java.io.Serializable;

public abstract class ParameterHandler<T> {
    public abstract void apply(T value, String key, Bundle bundle);

    public static class IntHandler extends ParameterHandler<Integer> {
        @Override
        public void apply(Integer value, String key, Bundle bundle) {
            bundle.putParcelable(key, new Par.ParInt(int.class.getName(), value));
        }
    }

    public static class DoubleHandler extends ParameterHandler<Double> {
        @Override
        public void apply(Double value, String key, Bundle bundle) {
            bundle.putParcelable(key, new Par.ParDouble(double.class.getName(), value));
        }
    }

    public static class LongHandler extends ParameterHandler<Long> {
        @Override
        public void apply(Long value, String key, Bundle bundle) {
            bundle.putParcelable(key, new Par.ParLong(long.class.getName(), value));
        }
    }

    public static class ShortHandler extends ParameterHandler<Short> {
        @Override
        public void apply(Short value, String key, Bundle bundle) {
            bundle.putParcelable(key, new Par.ParShort(short.class.getName(), value));
        }
    }

    public static class FloatHandler extends ParameterHandler<Float> {
        @Override
        public void apply(Float value, String key, Bundle bundle) {
            bundle.putParcelable(key, new Par.ParFloat(float.class.getName(), value));
        }
    }

    public static class ByteHandler extends ParameterHandler<Byte> {
        @Override
        public void apply(Byte value, String key, Bundle bundle) {
            bundle.putParcelable(key, new Par.ParByte(byte.class.getName(), value));
        }
    }

    public static class ByteArrayHandler extends ParameterHandler<byte[]> {

        @Override
        public void apply(byte[] value, String key, Bundle bundle) {
            bundle.putParcelable(key, new Par.ParByteArray(byte[].class.getName(), value));
        }
    }

    public static class BooleanHandler extends ParameterHandler<Boolean> {
        @Override
        public void apply(Boolean value, String key, Bundle bundle) {
            bundle.putParcelable(key, new Par.ParBoolean(boolean.class.getName(), value));
        }
    }

    public static class StringHandler extends ParameterHandler<String> {
        @Override
        public void apply(String value, String key, Bundle bundle) {
            bundle.putParcelable(key, new Par.ParString(value.getClass().getName(), value));
        }
    }

    public static class ParcelableHandler extends ParameterHandler<Parcelable> {
        @Override
        public void apply(Parcelable value, String key, Bundle bundle) {
            bundle.putParcelable(key, new Par.ParParcelable(value.getClass().getName(), value));
        }
    }

    public static class ParcelFileDescriptorHandler extends ParameterHandler<ParcelFileDescriptor> {

        @Override
        public void apply(ParcelFileDescriptor value, String key, Bundle bundle) {

        }
    }

    public static class SerializableHandler extends ParameterHandler<Serializable> {
        @Override
        public void apply(Serializable value, String key, Bundle bundle) {
            bundle.putParcelable(key, new Par.ParSerializable(value.getClass().getName(), value));
        }
    }
}
