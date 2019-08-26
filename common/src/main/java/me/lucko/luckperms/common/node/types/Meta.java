/*
 * This file is part of LuckPerms, licensed under the MIT License.
 *
 *  Copyright (c) lucko (Luck) <luck@lucko.me>
 *  Copyright (c) contributors
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package me.lucko.luckperms.common.node.types;

import me.lucko.luckperms.common.node.AbstractNode;
import me.lucko.luckperms.common.node.AbstractNodeBuilder;
import me.lucko.luckperms.common.node.factory.NodeFactory;

import net.luckperms.api.context.ImmutableContextSet;
import net.luckperms.api.node.metadata.NodeMetadataKey;
import net.luckperms.api.node.types.MetaNode;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Map;
import java.util.Objects;

public class Meta extends AbstractNode<MetaNode, MetaNode.Builder> implements MetaNode {
    private final String metaKey;
    private final String metaValue;

    public Meta(String metaKey, String metaValue, boolean value, long expireAt, ImmutableContextSet contexts, Map<NodeMetadataKey<?>, Object> metadata) {
        super(NodeFactory.metaNode(metaKey, metaValue), value, expireAt, contexts, metadata);
        this.metaKey = metaKey;
        this.metaValue = metaValue;
    }

    @Override
    public @NonNull String getMetaKey() {
        return this.metaKey;
    }

    @Override
    public @NonNull String getMetaValue() {
        return this.metaValue;
    }

    @Override
    public @NonNull Builder toBuilder() {
        return new Builder(this.metaKey, this.metaValue, this.value, this.expireAt, this.contexts, this.metadata);
    }

    public static final class Builder extends AbstractNodeBuilder<MetaNode, MetaNode.Builder> implements MetaNode.Builder {
        private String metaKey;
        private String metaValue;

        public Builder() {
            this.metaKey = null;
            this.metaValue = null;
        }

        public Builder(String metaKey, String metaValue, boolean value, long expireAt, ImmutableContextSet context, Map<NodeMetadataKey<?>, Object> metadata) {
            super(value, expireAt, context, metadata);
            this.metaKey = metaKey;
            this.metaValue = metaValue;
        }

        @Override
        public @NonNull Builder key(@NonNull String key) {
            this.metaKey = Objects.requireNonNull(key, "key");
            return this;
        }

        @Override
        public @NonNull Builder value(@NonNull String value) {
            this.metaKey = Objects.requireNonNull(value, "value");
            return this;
        }

        @Override
        public @NonNull Meta build() {
            Objects.requireNonNull(this.metaKey, "metaKey");
            Objects.requireNonNull(this.metaValue, "metaValue");
            return new Meta(this.metaKey, this.metaValue, this.value, this.expireAt, this.context.build(), this.metadata);
        }
    }
}