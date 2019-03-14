/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.internal.utils;

import org.mule.runtime.api.el.BindingContext;
import org.mule.runtime.api.metadata.DataType;
import org.mule.runtime.api.metadata.MediaType;
import org.mule.runtime.api.metadata.TypedValue;

import java.io.InputStream;

public class CoupaUtils {

    private CoupaUtils() {}

    private static final DataType APPLICATION_JSON = DataType.builder().type(InputStream.class).mediaType(MediaType.APPLICATION_JSON).build();
    private static final DataType IMAGE_PNG = DataType.builder().type(InputStream.class).mediaType(MediaType.create("image", "png")).build();

    public static BindingContext getBindingContext(Object entity) {
        return BindingContext.builder().addBinding("payload", new TypedValue<>(entity, APPLICATION_JSON)).build();
    }

    public static BindingContext getJavaBindingContext(Object entity) {
        return BindingContext.builder().addBinding("payload", new TypedValue<>(entity, DataType.OBJECT)).build();
    }

    public static BindingContext getBindingContextStream(InputStream entity) {
        return BindingContext.builder().addBinding("payload", new TypedValue<>(entity, IMAGE_PNG)).build();
    }



}
