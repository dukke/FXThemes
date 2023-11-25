package impl.com.pixelduke.window.win32.windows10;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

//        struct WINDOWCOMPOSITIONATTRIBDATA
//        {
//            WINDOWCOMPOSITIONATTRIB Attrib;
//            PVOID pvData;
//            SIZE_T cbData;
//        };
public class WindowCompositionAttributeData extends Structure implements Structure.ByReference {
    public int Attribute;
    public Pointer Data;
    public int SizeOfData;

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("Attribute", "Data", "SizeOfData");
    }
}
