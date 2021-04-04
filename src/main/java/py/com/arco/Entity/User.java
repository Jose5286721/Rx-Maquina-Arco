package py.com.arco.Entity;

public class User {
    private Long id;
    private String name;
    private String email;
    private String email_verified_at;
    private String celular;
    private Long sucursal_id;
    private Long role_id;
    private String fecha_nacimiento;
    private String cedula;
    private String avatar_link;
    private String nro_registro;
    private String sexo;
    private String deleted_at;
    private String created_at;
    private String updated_at;

    public User() {
    }

    public User(Long id, String name, String email, String email_verified_at, String celular, Long sucursal_id, Long role_id, String fecha_nacimiento, String cedula, String avatar_link, String nro_registro, String sexo, String deleted_at, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.email_verified_at = email_verified_at;
        this.celular = celular;
        this.sucursal_id = sucursal_id;
        this.role_id = role_id;
        this.fecha_nacimiento = fecha_nacimiento;
        this.cedula = cedula;
        this.avatar_link = avatar_link;
        this.nro_registro = nro_registro;
        this.sexo = sexo;
        this.deleted_at = deleted_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public void setEmail_verified_at(String email_verified_at) {
        this.email_verified_at = email_verified_at;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Long getSucursal_id() {
        return sucursal_id;
    }

    public void setSucursal_id(Long sucursal_id) {
        this.sucursal_id = sucursal_id;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getAvatar_link() {
        return avatar_link;
    }

    public void setAvatar_link(String avatar_link) {
        this.avatar_link = avatar_link;
    }

    public String getNro_registro() {
        return nro_registro;
    }

    public void setNro_registro(String nro_registro) {
        this.nro_registro = nro_registro;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}

