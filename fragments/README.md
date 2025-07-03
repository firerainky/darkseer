# fragments 说明

## JSON 深度嵌套排序

`test/sortingJSON`

在 AK/SK 的认证模式中对 request body 内容做签名验证，需要保证 request body 的字符串顺序完全一致。

所以要对 JSON 内容做深度嵌套排序

JAVA 中的 TreeNode 也能排序，但它只能对第一层进行排序，无法满足嵌套排序的要求

`com.fasterxml.jackson.databind.ObjectMapper` jackson-datamind 来自于 spring-boot-starter-web 的依赖