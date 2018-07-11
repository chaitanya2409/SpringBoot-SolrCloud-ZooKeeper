package com.test.util;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.internal.util.compare.EqualsHelper;
import org.hibernate.usertype.UserType;

public class EncryptionTypeUtil implements UserType {
    static final int sqlType = Types.VARCHAR;
    static final int[] sqlTypes = new int[] { sqlType };

    public final int[] sqlTypes() {
        return sqlTypes.clone();
    }

    public final boolean equals(final Object x, final Object y) throws HibernateException {
        return EqualsHelper.equals(x, y);
    }

    public final int hashCode(final Object x) throws HibernateException {
        return x.hashCode();
    }

    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor si, Object owner)
            throws HibernateException, SQLException {
        checkInitialization();
        final String message = rs.getString(names[0]);
        return rs.wasNull() ? null : convertToObject(decrypt(message));
    }

    protected Object convertToObject(final String string) {
        return string;
    }

    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor si)
            throws HibernateException, SQLException {
        checkInitialization();
        if (value == null) {
            st.setNull(index, sqlType);
        } else {
            st.setString(index, encrypt(convertToString(value)));
        }
    }

    public String encrypt(String str) {
        String str1 = "";
        try {
            if (null != str && !str.equals("")) {
                byte[] b = org.apache.commons.codec.binary.Base64.encodeBase64(str.getBytes());
                str1 = new String(b);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str1;
    }

    public String decrypt(String str) {

        try {
            if (null != str && !str.equals("")) {
                byte[] b = str.getBytes();
                str = new String(org.apache.commons.codec.binary.Base64.decodeBase64(b));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }

    protected String convertToString(final Object object) {
        return object == null ? null : object.toString();
    }

    private void checkInitialization() {

    }

    public final Object deepCopy(final Object value) throws HibernateException {
        return value;
    }

    public boolean isMutable() {
        return false;
    }

    public Serializable disassemble(Object value) throws HibernateException {
        if (value == null) {
            return null;
        }
        return (Serializable) deepCopy(value);
    }

    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        if (cached == null) {
            return null;
        }
        return deepCopy(cached);
    }

    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

    public Class returnedClass() {
        // TODO Auto-generated method stub
        return null;
    }

}
