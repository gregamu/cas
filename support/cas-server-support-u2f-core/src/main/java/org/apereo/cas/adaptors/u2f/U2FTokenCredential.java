package org.apereo.cas.adaptors.u2f;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;

import org.apereo.cas.authentication.Credential;

import java.io.Serializable;

/**
 * This is {@link U2FTokenCredential}.
 *
 * @author Misagh Moayyed
 * @since 5.1.0
 */
public class U2FTokenCredential implements Credential, Serializable {
    private static final long serialVersionUID = -970682410132111037L;

    private String token;

    public U2FTokenCredential() {
    }

    public U2FTokenCredential(final String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE)
                .append("token", this.token)
                .toString();
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof U2FTokenCredential)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        final U2FTokenCredential other = (U2FTokenCredential) obj;
        final EqualsBuilder builder = new EqualsBuilder();
        builder.append(this.token, other.token);
        return builder.isEquals();
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder(97, 31);
        builder.append(this.token);
        return builder.toHashCode();
    }

    @Override
    public String getId() {
        return this.token;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    /**
     * Validate.
     *
     * @param context the context
     */
    public void validate(final ValidationContext context) {
        if (!StringUtils.isNotBlank(getId())) {
            final MessageContext messages = context.getMessageContext();
            messages.addMessage(new MessageBuilder()
                .error()
                .source("token")
                .defaultText("Unable to accept credential with an empty or unspecified token")
                .build());
        }
    }
}
