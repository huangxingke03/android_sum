precision mediump float;
uniform sampler2D uBaseTex;
uniform sampler2D uMaskTex;
uniform vec4 uTintColor;   // 目标染色颜色 (RGBA)
varying vec2 vTexCoord;

void main() {
    vec4 baseColor = texture2D(uBaseTex, vTexCoord);
    vec4 maskColor = texture2D(uMaskTex, vTexCoord);

    // 取 mask 的透明度作为染色强度
    float alpha = maskColor.a;
    vec3 tinted = mix(baseColor.rgb, uTintColor.rgb, alpha);

    gl_FragColor = vec4(tinted, 1.0);
}
