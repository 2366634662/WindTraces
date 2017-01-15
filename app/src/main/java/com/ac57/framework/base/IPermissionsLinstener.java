package com.ac57.framework.base;

import java.util.List;

/**
 * Created by Du_Li on 2017/1/1.
 */

public interface IPermissionsLinstener {
    void permissionSuccess();

    void permissionDenied(List<String> deniedPermissions);

}
