<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Report extends Model
{
    use HasFactory;

    // Allow all fields to be filled
    protected $guarded = ['id'];

    // A report belongs to a User
    public function user()
    {
        return $this->belongsTo(User::class);
    }

    // A report has many Comments
    public function comments()
    {
        return $this->hasMany(Comment::class);
    }

    // A report has many Likes
    public function likes()
    {
        return $this->hasMany(Like::class);
    }
}